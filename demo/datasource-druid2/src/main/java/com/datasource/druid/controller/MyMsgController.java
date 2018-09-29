package com.datasource.druid.controller;

import com.datasource.druid.service.MyMsgService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping(value="/myMsg")
public class MyMsgController {
    private Logger logger =Logger.getLogger(MyMsgController.class);


    @Autowired
    private MyMsgService myMsgService;
    @ResponseBody
    @RequestMapping(value="/getMsgById/{id}",method = RequestMethod.GET)
    public String getMyMsgById(@PathVariable("id")Long id){
       return  myMsgService.getMyMsgById(id).toString();
    }

    @ResponseBody
    @GetMapping(value="/getAllMsg")
    public String getAllMsg(){
        logger.error(">>>>>>>>>>>>>>>>>>>>.ces时间"+System.currentTimeMillis());
        return myMsgService.getAll().size()>0?myMsgService.getAll().toString():"";
    }
}
