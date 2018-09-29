package com.datasource.multi.druid.service;

import com.datasource.multi.druid.model.ds1.MyMsgModel;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowMsgServiceImpTest {
    private static final  Logger logger=Logger.getLogger(ShowMsgServiceImpTest.class);

    @Autowired
    private ShowMsgService showMsgService;
    @Test
    public void showMsg() {
        logger.info(">>>>>>>>>>>>>>>>>>.测试开始》》》》》》》》》》》》。。");
        Map<String,Object> map=showMsgService.showMsg(1008L,"wang","EAZ216","UPTYPE");
        logger.info(">>>>>.msg:"+map.toString());
        Assert.assertEquals("mybatis",((MyMsgModel)map.get("myMsg")).getMsgName());
    }
}