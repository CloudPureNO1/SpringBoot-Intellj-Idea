package com.datasource.multi.druid.controller;

import com.datasource.multi.druid.service.ShowMsgService;
import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(value="/showMsg")
public class ShowMsgController {
    private static final Logger logger=Logger.getLogger(ShowMsgController.class);

    @Autowired
    private ShowMsgService showMsgService;

    @GetMapping("/msg/{msgId}/{userName}")
    @ResponseBody
    public Map<String,Object> showMsg(@PathVariable("msgId")Long id , @PathVariable("userName")String userName, @RequestParam("aaa100DS2")String aaa100DS2,@RequestParam("aaa100DS3")String aaa100DS3){
      return  showMsgService.showMsg(id,userName,aaa100DS2,aaa100DS3);
    }
}
