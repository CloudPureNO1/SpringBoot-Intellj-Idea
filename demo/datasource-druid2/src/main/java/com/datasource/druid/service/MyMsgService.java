package com.datasource.druid.service;

import com.datasource.druid.model.MyMsgModel;

import java.util.List;

public interface MyMsgService {
    MyMsgModel getMyMsgById(Long id);
    List<MyMsgModel> getAll();
}
