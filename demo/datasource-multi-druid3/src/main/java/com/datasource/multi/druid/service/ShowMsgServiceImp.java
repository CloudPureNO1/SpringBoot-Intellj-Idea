package com.datasource.multi.druid.service;

import com.datasource.multi.druid.mapper.ds1.MyMsgMapper;
import com.datasource.multi.druid.mapper.ds1.MyUsersMapper;
import com.datasource.multi.druid.mapper.ds2.Aa09MapperDS2;
import com.datasource.multi.druid.mapper.ds3.Aa09MapperDS3;
import com.datasource.multi.druid.model.ds1.MyMsgModel;
import com.datasource.multi.druid.model.ds1.MyUsersModel;
import com.datasource.multi.druid.model.ds2.Aa09ModelDS2;
import com.datasource.multi.druid.model.ds3.Aa09ModelDS3;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ShowMsgServiceImp implements ShowMsgService {
    private static final  Logger logger=Logger.getLogger(ShowMsgServiceImp.class);

    @Autowired
    private MyMsgMapper myMsgMapper;

    @Autowired
    private MyUsersMapper myUsersMapper;

    @Autowired
    private Aa09MapperDS2 aa09MapperDS2;

    @Autowired
    private Aa09MapperDS3 aa09MapperDS3;



    @Override
    public Map<String, Object> showMsg(Long msgId, String userName, String aaa100DS2, String aaa100DS3) {
        logger.info(">>>>>>>>>>>>>>"+msgId+">>>>>>"+userName+">>>>>>"+aaa100DS2+">>>>>>>>"+aaa100DS3);
        Assert.assertEquals("msgId不是Long类型",Long.class,msgId.getClass());
        Assert.assertEquals("userName不是String类型",String.class,userName.getClass());
        Assert.assertEquals("aaa100DS2不是String类型",String.class,aaa100DS2.getClass());
        Assert.assertEquals("aaa100DS3不是String类型",String.class,aaa100DS3.getClass());
        MyMsgModel myMsgModel=myMsgMapper.getMsgById(msgId);
        List<MyUsersModel> myUsersModelList=myUsersMapper.getUsersByName(userName);
        List<Aa09ModelDS2> aa09ModelDS2List=aa09MapperDS2.getAa09ByAaa100(aaa100DS2);
        List<Aa09ModelDS3> aa09ModelDS3List=aa09MapperDS3.getAa09ByAaa100(aaa100DS3);
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("myMsg",myMsgModel);
        map.put("users",myUsersModelList);
        map.put("aa09DS2",aa09ModelDS2List);
        map.put("aa09DS",aa09ModelDS3List);
        Assert.assertNotEquals(null,map);
        return map;
    }
}
