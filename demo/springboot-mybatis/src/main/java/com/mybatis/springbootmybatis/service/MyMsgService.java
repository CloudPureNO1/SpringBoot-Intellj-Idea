package com.mybatis.springbootmybatis.service;

import com.mybatis.springbootmybatis.model.MyMsg;

import java.util.List;

public interface MyMsgService {
    int addMsg(String id,String name,String detail);
    int addMsgByBean(MyMsg bean);
    MyMsg getMsgById(String id);
    List<MyMsg> getAll();
}
