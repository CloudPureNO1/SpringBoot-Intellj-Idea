package com.datasource.druid.controller;

import com.datasource.druid.model.MyMsgModel;
import com.datasource.druid.service.MyMsgService;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/msg")
public class MyMsgController {
    private static final  Logger logger=Logger.getLogger(MyMsgController.class);
    @Autowired
    private MyMsgService myMsgService;

    @GetMapping("/getMsgById/{id}/{testMsg}")
    @ResponseBody
    public MyMsgModel getMsgById(@PathVariable("id")Long id,@PathVariable("testMsg")String testMsg){
        logger.info(">>>>>>>>>>>>>>>>>id的值为："+id);
        logger.info(">>>>>>>>>>>>>>>>>testMsg的值为："+testMsg);
        MyMsgModel myMsgModel=myMsgService.getMsgById(id);
        Assert.assertNotEquals(null,myMsgModel);
        return myMsgModel;
    }
}
