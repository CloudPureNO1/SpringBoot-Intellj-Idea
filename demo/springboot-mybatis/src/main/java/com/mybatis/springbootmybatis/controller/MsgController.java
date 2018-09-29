package com.mybatis.springbootmybatis.controller;

import com.mybatis.springbootmybatis.model.MyMsg;
import com.mybatis.springbootmybatis.service.MyMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/msg")
public class MsgController {
    @Autowired
    private MyMsgService myMsgService;

    @RequestMapping(value="/add",method = RequestMethod.GET)
    public String addMsg(@RequestParam(value="id")String id,@RequestParam(value="name")String name,@RequestParam(value="detail")String detail){
       return myMsgService.addMsg(id,name,detail)==1?"新增数据成功！":"新增数据失败！";
    }

    @RequestMapping(value="/addBean",method = RequestMethod.POST)
    public String addMsgByBean(@RequestBody MyMsg bean){
        return myMsgService.addMsgByBean(bean)==1?"新增数据成功！":"新增数据失败！";
    }

    @RequestMapping(value="/showMsgById",method=RequestMethod.GET)
    public String  getMsgById(@RequestParam(value="id")String id){
        return myMsgService.getMsgById(id)==null?"没有对应的数据":myMsgService.getMsgById(id).toString();
    }

    @RequestMapping(value="/getAll",method = RequestMethod.GET)
    public String getAll(){
        String str="查询结果：>>>>";
        List<MyMsg> list= myMsgService.getAll();
        if(list!=null&&list.size()>0){
            for(MyMsg bean:list){
                str+=bean.toString()+"****";
            }
            return str;
        }
        return "没有查询导数据！";
    }

}

