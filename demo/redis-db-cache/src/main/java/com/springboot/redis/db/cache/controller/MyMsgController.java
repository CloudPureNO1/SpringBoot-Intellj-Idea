package com.springboot.redis.db.cache.controller;

import com.springboot.redis.db.cache.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="/myMsg")
public class MyMsgController {
    @Autowired
    private MsgService msgService;

    @RequestMapping(value="/getMsg/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getMsg(@PathVariable("id")Long id){
        return msgService.getMsg(id).toString();
    }
}
