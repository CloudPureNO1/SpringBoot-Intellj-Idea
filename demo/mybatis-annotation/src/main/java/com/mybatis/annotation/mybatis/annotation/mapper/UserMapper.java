package com.mybatis.annotation.mybatis.annotation.mapper;

import com.mybatis.annotation.mybatis.annotation.model.UserModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {

    @Insert("insert into my_users(user_id,user_name,user_pwd) values(#{userId},#{userName},#{userPwd})")
    @SelectKey(statement = "select  squ_users.nextval from dual",keyProperty = "userId",keyColumn = "user_id",before = true,resultType =long.class)
    int insert(UserModel userModel);

    @Insert("insert into my_users(user_id,user_name,user_pwd) values(#{userId},#{userName},#{userPwd})")
    @SelectKey(statement = "select  squ_users.nextval from dual",keyProperty = "userId",keyColumn = "user_id",before = true,resultType =long.class)
    int insertBySequence(UserModel userModel);

    @Insert("insert into my_users(user_id,user_name,user_pwd) values(#{userId},#{userName},#{userPwd})")
    @SelectKey(statement = "select squ_users.nextval from dual",keyProperty = "userId",keyColumn = "user_id",before = true,resultType =long.class)
    int insertBySquParam(@Param("userName")String userName,@Param("userPwd")String userPwd);

    @Insert("insert into my_users (user_id,user_name,user_pwd) values(#{userId},#{userName},#{userPwd})")
    @SelectKey(statement = "select squ_users.nextval from dual",keyColumn = "user_id",keyProperty = "userId",before = true,resultType = long.class)
    int insertByMap(Map<String,Object> map);

    @Select("select * from my_users where user_id=#{userId}")
    @Results({
            @Result(property = "userId",column = "user_id",id=true),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "userPwd",column = "user_pwd")
    })
    UserModel getUserById(@Param("userId") Long id);

    @Select("select * from my_users")
    @Results({
            @Result(property = "userId",column = "user_id",id=true),
            @Result(property = "userName",column = "user_name"),
            @Result(property = "userPwd",column = "user_pwd")
    })
    List<UserModel> getAllUser();
}
