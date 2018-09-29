package com.datasource.multi.druid.mapper.ds1;

import com.datasource.multi.druid.model.ds1.MyMsgModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper //没有@Mapper注解，就需要在启动类上添加自动烧苗的路径
@Repository //没有这个持久层bean注解，虽然能编译通过，但是idea会在自动注入的地方显示红线（很不爽）
public interface MyMsgMapper {
    @Select("select * from my_msg where msg_id=#{id}")
    @Results({
            @Result(id=true,column = "msg_id",property = "msgId"),
            @Result(column = "msg_name",property = "msgName"),
            @Result(column = "msg_detail",property = "msgDetail")
    })
    MyMsgModel getMsgById(@Param("id")Long id);

}
