package com.datasource.druid.mapper;

import com.datasource.druid.model.MyMsgModel;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper     //没有mapper就要在启动类中添加自动扫描
@Repository //与@Congfiguration  @Component  是标注bean的注解，@Repository 是持久层的注解，没有这个，自动注入的时候idea中提示红线
public interface MyMsgMapper {
    @Select("select * from my_msg where msg_id=#{id}")
    @Results({//如果不配置还回结果集，得到的值为null
            @Result(id = true,column = "msg_id",property = "msgId"),
            @Result(column = "msg_name",property = "msgName"),
            @Result(column = "msg_detail",property = "msgDetail")
    })
    MyMsgModel getMsgById(@Param("id") Long id);
}
