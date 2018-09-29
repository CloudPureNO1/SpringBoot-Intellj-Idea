package com.indi.demo.db.service.imp;

import com.indi.demo.db.mapper.ds1.MyMsgMapper;
import com.indi.demo.db.mapper.ds2.NetAb01Mapper;
import com.indi.demo.db.mapper.ds3.Aa09Mapper;
import com.indi.demo.db.service.ShowMsgService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;


import java.util.HashMap;
import java.util.Map;

@Service
public class ShowMsgServiceImp implements ShowMsgService {
    private static final Logger logger=Logger.getLogger(ShowMsgServiceImp.class);

    @Autowired
    private MyMsgMapper myMsgMapper;
    @Autowired
    private NetAb01Mapper ab01Mapper;
    @Autowired
    private Aa09Mapper aa09Mapper;

    @Override
    @Cacheable(cacheNames = "msg",keyGenerator = "getRedisKeyGenerator")
    public Map<String, Object> showMsg(Long msgId, String aaa100) {
        logger.info(">>>>>>>>>>>>*******************************>>>>>>>>");
        logger.info(">>>>>>>>>>>>>>>>>>获取数据开始>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Assert.assertEquals("传入的msgId,不是Long类型",Long.class,msgId.getClass());
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("msg",myMsgMapper.getMsgById(1008L));
        logger.info(">>>>>>>>>>>>>>>>>>myMsgMapper调用结束>>>>>>>>>>>>>>>>>>>>>>>>>");
        map.put("ab01List",ab01Mapper.getAll());
        logger.info(">>>>>>>>>>>>>>>>>>ab01Mapper调用结束>>>>>>>>>>>>>>>>>>>>>>>>>");
        map.put("aa09List",aa09Mapper.getAa09ByAaa100(aaa100));
        logger.info(">>>>>>>>>>>>>>>>>>aa09Mapper调用结束>>>>>>>>>>>>>>>>>>>>>>>>>");
        logger.info(">>>>>>>>>>>>>>>>>>获取数据结束>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return map;
    }


    @Override
    @Cacheable(cacheNames = "msg2",key="#p0")
    public Map<String, Object> showMsg2(Long msgId, String aaa100) {
        logger.info(">>>>>>>>>>>>***********没有缓存时调用********************>>>>>>>>");
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("msg",myMsgMapper.getMsgById(1008L));
        map.put("ab01List",ab01Mapper.getAll());
        map.put("aa09List",aa09Mapper.getAa09ByAaa100(aaa100));
        logger.info(">>>>>>>>>>>>>>>>>>获取数据结束>>>>>>>>>>>>>>>>>>>>>>>>>>");
        return map;
    }
}
