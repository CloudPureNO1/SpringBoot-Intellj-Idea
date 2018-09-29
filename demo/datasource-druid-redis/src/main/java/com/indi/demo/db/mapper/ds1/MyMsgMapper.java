package com.indi.demo.db.mapper.ds1;

import com.indi.demo.db.model.ds1.MyMsgModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper   //标识项目编译、运行时，自动扫描这个接口，如果不配置这个注解，就需要在启动类上添加自动扫描的路径
@Repository //持久层实例bean注解，一般在实现类中，此处用的是mybatis 没有对应的实现类，所以加在接口上，如果不添加这个注解，编译和运行都正常，但是idea会显示下划线，很不爽
public interface MyMsgMapper {

    @Select("select * from my_msg where msg_id=#{id}")
    @Results({
            @Result(id=true,column = "msg_id",property = "msgId"),
            @Result(column = "msg_name",property = "msgName")
    })
    MyMsgModel getMsgById(@Param("id") Long id);

}


