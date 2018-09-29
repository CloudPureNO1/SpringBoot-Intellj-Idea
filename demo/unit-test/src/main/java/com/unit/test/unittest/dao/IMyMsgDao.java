package com.unit.test.unittest.dao;

import com.unit.test.unittest.bean.MyMsgBean;

import java.util.List;

public interface IMyMsgDao {
    int add(MyMsgBean bean);
    int del(Long id);
    int chg(MyMsgBean bean);
    MyMsgBean findBeanById(Long id);
    List<MyMsgBean> findAll();
}
