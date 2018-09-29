package com.unit.test.unittest.service;

import com.unit.test.unittest.bean.MyMsgBean;

import java.util.List;

public interface MyMsgService {
    boolean dmlMyMsg(MyMsgBean bean,String Type);
    MyMsgBean findBeanById(Long Id);
    List<MyMsgBean> findAll();
}
