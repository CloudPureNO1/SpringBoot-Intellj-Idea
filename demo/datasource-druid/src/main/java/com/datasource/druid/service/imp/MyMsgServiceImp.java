package com.datasource.druid.service.imp;

import com.datasource.druid.mapper.MyMsgMapper;
import com.datasource.druid.model.MyMsgModel;
import com.datasource.druid.service.MyMsgService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyMsgServiceImp implements MyMsgService {
    private static final  Logger logger=Logger.getLogger(MyMsgServiceImp.class);
    @Autowired
    private MyMsgMapper myMsgMapper;
    @Override
    public MyMsgModel getMsgById(Long id) {
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>>"+Long.class);
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>id:"+id.getClass());
        Assert.assertEquals("传入的id不是Long类型的值",Long.class,id.getClass());
        return myMsgMapper.getMsgById(id);
    }
}
