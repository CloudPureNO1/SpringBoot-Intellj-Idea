package com.datasource.druid.service.imp;

import com.datasource.druid.mapper.MyMsgMapper;
import com.datasource.druid.model.MyMsgModel;
import com.datasource.druid.service.MyMsgService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class MyMsgServiceImp implements MyMsgService {
    private static final Logger logger=Logger.getLogger(MyMsgServiceImp.class);
    @Autowired
    private MyMsgMapper myMsgMapper;
    @Override
    public MyMsgModel getMyMsgById(Long id) {
        logger.info(">>>>>>>>>>>>>The class of Long type："+Long.class);
        logger.info(">>>>>>>>>>>>>The class of id:"+id.getClass());
        Assert.assertEquals(Long.class,id.getClass());
        logger.info(">>>>>>>>>>>>>>>>id的类型是Long类型的！");
        return myMsgMapper.getMyMsgById(id);
    }

    @Override
    public List<MyMsgModel> getAll() {
        return myMsgMapper.getAll();
    }
}
