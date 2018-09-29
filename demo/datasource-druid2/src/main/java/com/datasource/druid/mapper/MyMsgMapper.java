package com.datasource.druid.mapper;

import com.datasource.druid.model.MyMsgModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/*@Repository*/
/*@Mapper*/
/*@Repository也可以不用添加，但是不添加之后使用@Autowired注入接口是IDEA会提示红线，但是不影响编译运行*/
/*@Mapper如果配置了自动扫描，这个注解可以不添加*/
@Mapper
@Repository
public interface MyMsgMapper {
    @Select("select * from my_msg where msg_id=#{id}")
    @Results({
            @Result(id=true,column = "msg_id",property = "msgId"),
            @Result(column = "msg_name",property = "msgName"),
            @Result(column = "msg_detail",property = "msgDetail")
    })
    MyMsgModel getMyMsgById(@Param("id") Long id);


    @Select("select * from my_msg")
    @Results({
            @Result(id=true,column = "msg_id",property = "msgId"),
            @Result(column = "msg_name",property = "msgName"),
            @Result(column = "msg_detail",property = "msgDetail")
    })
    List<MyMsgModel> getAll();
}
