package com.datasource.multi.druid.mapper.ds1;

import com.datasource.multi.druid.model.ds1.MyUsersModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper //没有这个@Mapper注解，就需要在启动类生添加自动扫描的路径
@Repository //没有持久层@Repository注解，在自动注入的地方，会显示红色下划线（很不爽）
public interface MyUsersMapper {
  //  @Select("select * from my_users where user_name like '%${userName}%'")  //模糊查询一
    @Select("select * from my_users where user_name like concat(#{userName},'%')")  //模糊查询二  like  wang%
    @Results({//没有结果集类型，集合中返回的就是对象是null
            @Result(id=true,column = "user_id",property = "userId"),
            @Result(column = "user_name",property = "userName"),
            @Result(column = "user_pwd",property = "userPwd")
    })
    List<MyUsersModel> getUsersByName(@Param("userName") String userName);
}
