package com.indi.demo.db.controller;

import com.indi.demo.db.service.ShowMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping(value="/msg")
public class ShowMsgController {
    @Autowired
    private ShowMsgService showMsgService;

    @ResponseBody
    @GetMapping("/showMsg/{id}/{aaa100}")
    public Map<String,Object> showMsg(@PathVariable("id")Long id,@PathVariable("aaa100")String aaa100){
        return showMsgService.showMsg(id,aaa100);
    }
}
