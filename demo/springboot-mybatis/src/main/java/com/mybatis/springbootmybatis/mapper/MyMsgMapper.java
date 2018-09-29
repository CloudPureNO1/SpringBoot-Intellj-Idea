package com.mybatis.springbootmybatis.mapper;

import com.mybatis.springbootmybatis.model.MyMsg;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Mapper
public interface MyMsgMapper {

    @Insert("insert into my_msg (msg_id,msg_name,msg_detail) values(#{msgId},#{msgName},#{msgDetail})")
    @Transactional
    int addMsg(@Param("msgId")Long msgId,@Param("msgName")String msgName,@Param("msgDetail")String msgDetail);

    @Insert("insert into my_msg (msg_id,msg_name,msg_detail) values(#{msgId},#{msgName},#{msgDetail})")
    @Transactional
    int addMsgByBean(MyMsg bean);

    @Select("select * from my_msg where msg_id=#{msgId}")
    @Results({
            @Result(property = "msgId",column = "msg_id"),
            @Result(property = "msgName",column = "msg_name"),
            @Result(property = "msgDetail",column = "msg_detail")
    })
    MyMsg getMsgById(@Param("msgId") Long msgId);

    @Select("select * from my_msg")
    @Results({
            @Result(property = "msgId",column = "msg_id"),
            @Result(property = "msgName",column = "msg_name"),
            @Result(property = "msgDetail",column = "msg_detail")
    })
    List<MyMsg> getAll();

}
