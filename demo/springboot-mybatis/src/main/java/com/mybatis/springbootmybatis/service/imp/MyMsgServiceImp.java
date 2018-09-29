package com.mybatis.springbootmybatis.service.imp;

import com.mybatis.springbootmybatis.mapper.MyMsgMapper;
import com.mybatis.springbootmybatis.model.MyMsg;
import com.mybatis.springbootmybatis.service.MyMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyMsgServiceImp implements MyMsgService {
    @Autowired
    private MyMsgMapper myMsgMapper;
    @Override
    public int addMsg(String id, String name, String detail) {
        return myMsgMapper.addMsg(Long.valueOf(id),name,detail);
    }

    @Override
    public int addMsgByBean(MyMsg bean) {
        return myMsgMapper.addMsgByBean(bean);
    }

    @Override
    public MyMsg getMsgById(String id) {
        return myMsgMapper.getMsgById(Long.valueOf(id));
    }

    @Override
    public List<MyMsg> getAll() {
        return myMsgMapper.getAll();
    }
}
