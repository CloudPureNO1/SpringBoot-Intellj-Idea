package com.mybatis.annotation2.mytatis.annotation2.mapper;

import com.mybatis.annotation2.mytatis.annotation2.model.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 *  org.apache.ibatis.annotations.Mapper  注解的类
 *  mapper 类前添加@Mapper注解，否则需要在启动类添加扫描mapper的注解类（不推荐-能不再启动类添加东西就不要做）
 *  两个都不添加，编译时会报错
 */
@Mapper
public interface UserMapper {
    @Select("select * from my_users where user_id=#{id}")
    @Results({
            @Result(property = "userId",column = "user_id",id=true),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "userPwd",column = "user_pwd")
    })
    UserModel getUserById(Long id);

    /**
     * 查询的时候要设置结果集，类似于xml中的resultMap
     * @Reaults({
     *          @Result(property="",column="")
     *          })
     * @return
     */
    @Select("select * from my_users")
    @Results({
            @Result(property = "userId",column = "user_id",id=true),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "userPwd",column = "user_pwd")
    })
    UserModel getAll();

    @Insert("insert into my_users (user_id,user_name,user_pwd) values(#{userId},#{userName},#{userPwd})")
    @SelectKey(statement = "select squ_users.nextval from dual",keyProperty = "userId",keyColumn = "user_id",before = true,resultType = long.class)
    /**
     * 此处 插入方法中的变量名称，必须和#{}参数中的变量名称一致，此处传入userId的值，不插入到数据（因为@SelectKey)
     * 如果插入数据后，不需要取生成的userId，那么，可以不传入userId 参数：即
     * insert(String userName,String userPwd)
     * 如果参数中使用了@Param("变量名")，那么可以另外设置参数名（提倡）
     * int insert(@Param("userId") String id,@Param("userName") String userName,@Param("userPwd") String passwd);
     */
   // int insert(String userId, String userName, String userPwd);
    int insert(@Param("userId") Long userId, @Param("userName") String userName, @Param("userPwd") String userPwd);


    /**
     * 插入注解：@Insert  ：org.apache.ibatis.annotations.Insert
     * 取值注解：@SelectKey ： org.apache.ibatis.annotations.SelectKey;
     * 可以通过statement 属性执行对应语句（此处，用于查询生成序列号，作为自动的id,且在插入数据后，通过参数中的userId获得，生成的userId）
     * @return   int  执行的结果记录数，0为失败
     */
    /**
     * 采用实体bean ，作为参数时，bean必须包含于sql参数中一致的属性名，且有对应的getter和setter
     * @param userModel
     * @return
     */
    @Insert("insert into my_users (user_id,user_name,user_pwd) values(#{userId},#{userName},#{userPwd})")
    @SelectKey(statement = "select squ_users.nextval from dual",keyProperty = "userId",keyColumn = "user_id",before = true,resultType = long.class)
    int insertByModel(UserModel userModel);


    /**
     * 插入注解：@Insert  ：org.apache.ibatis.annotations.Insert
     * 取值注解：@SelectKey ： org.apache.ibatis.annotations.SelectKey;
     * 可以通过statement 属性执行对应语句（此处，用于查询生成序列号，作为自动的id,且在插入数据后，通过参数中的userId获得，生成的userId）
     * @return   int  执行的结果记录数，0为失败
     */
    /**
     * 采用Map 作为参数时，map中必须包含于sql参数中一致的 建-值对：比如：#{userName}--->map.get("userMap")
     * @param map
     * @return
     */
    @Insert("insert into my_users (user_id,user_name,user_pwd) values(#{userId},#{userName},#{userPwd})")
    @SelectKey(statement = "select squ_users.nextval from dual",keyProperty = "userId",keyColumn = "user_id",before = true,resultType = long.class)
    int insertByMap(Map<String,Object>map);

    /**
     * 批量插入不能返回每条的id
     * 采用脚本script 执行批量，里面的内容与 xml的文件中的基本一样(有些小调整)
     *      <insert id="insertForBatch" parameterType="java.util.List">
         *      <selectKey keyColumn="user_id" keyProperty="userId" order="BEFORE" resultType="Long">
         *      SELECT sue_users.nextval FROM dual
         *      </selectKey>
         *      insert into my_goods(goods_id,goods_name,goods_price,order_id)
         *      values
         *      <foreach collection="list" item="user" open="（" separator="," close=")" index="i">
         *      #{userId},
         *      #{user.userName},
         *      #{user.userPwd}
         *      </foreach>
     *      </insert>
     * @param userList
     * @return
     */
    @Insert({"<script>"
            + "insert into my_users(user_id,user_name,user_pwd)"
            +"<foreach collection=\"list\" item=\"user\"  separator=\"union all\"  index=\"i\">"
            + "select #{userId}, #{user.userName}, #{user.userPwd} from dual "
            +"</foreach>"
            +"</script>"})
    @SelectKey(statement = "select squ_users.nextval from dual",keyColumn = "user_id",keyProperty = "userId",before = true,resultType = long.class)
    int insertBatchByScript(List<UserModel>userList);//Available parameters are [userId, collection, list]



    /**
     * 批量插入不能返回每条的id
     * @InsertProvider  动态批量sql，采用内部类,
     * @param userList
     * @return
     */
    @InsertProvider(type = UserProvider.class,method="getProviderUserSql")
    @SelectKey(statement = "select squ_users.nextval from dual",keyColumn = "user_id",keyProperty = "userId",before = true,resultType = long.class)
    int providerInsert(List<UserModel> userList);


    /**
     * 建一个内部类，用于动态组装sql
     */
    class UserProvider{
        /**
         * 此处构建一个批量插入的动态sql
         * oracle  批量插入的方法是：
         * insert into my_users(user_id,user_name,user_pwd)
         * select * from ....
         * 那么此处，可以通过dual 来拼接list结果
         * insert into my_users(user_id,user_name,user_pwd)
         * select userId1,userName1,userPwd1 from dual
         * union all
         * select userId2,userName2,userPwd2 from dual
         * ......
         * @return
         */
       public  String  getProviderUserSql(Map<String,Object>map){
           int i=0;//用于计数，判断是否最后一条
           StringBuffer buf=new StringBuffer("insert into my_users(user_id,user_name,user_pwd) ");
           //取得 List<UserModel> userList
           List<UserModel> userList= (List<UserModel>) map.get("list");//对应forEach的list
           String userIds=map.get("userId").toString();//此处用于取出，@SelectKey生成的userId,否则就要自己在userModel中传入
           if(userList!=null&&userList.size()>0){
               for(UserModel user:userList){
                   i++;
                   if(i==userList.size()){
                       setSql(buf, userIds, user, "' from dual");
                   }else{
                       setSql(buf, userIds, user, "' from dual union all");
                   }
               }
           }
           return buf.toString();
       }

        public void setSql(StringBuffer buf, String userIds, UserModel user, String s) {
            buf.append(" select to_number(");
            buf.append(userIds);
            buf.append("),'");
            buf.append(user.getUserName());
            buf.append("','");
            buf.append(user.getUserPwd());
            buf.append(s);
        }
    }
}
