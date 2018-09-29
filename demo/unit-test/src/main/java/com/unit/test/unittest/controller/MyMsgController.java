package com.unit.test.unittest.controller;

import com.unit.test.unittest.bean.MyMsgBean;
import com.unit.test.unittest.service.MyMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/myMsg")
public class MyMsgController {
    @Autowired
    MyMsgService myMsgService;

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public List<MyMsgBean> findAll(){
       return myMsgService.findAll();
    }

    @RequestMapping(value="/{id}",method = RequestMethod.GET)
    public MyMsgBean findBeanById(@PathVariable("id") Long id){
        return myMsgService.findBeanById(id);
    }


    @RequestMapping(value="/dml",method =RequestMethod.GET)
    public String dmlMyMsg(@RequestParam(value="id") String id,@RequestParam(value="name") String name,@RequestParam(value="detail") String detail,@RequestParam(value="type") String type){
        MyMsgBean bean=new MyMsgBean(Long.valueOf(id),name,detail);
        boolean b=  myMsgService.dmlMyMsg(bean,type);
        if(b) return type+" success!";
        return type+" failed!";
    }

    @RequestMapping(value="/addBean",method = RequestMethod.POST)
    public String addBean(@RequestParam(value="id")String id,@RequestParam(value="name")String name,@RequestParam(value="detail")String detail){
        MyMsgBean bean=new MyMsgBean(Long.valueOf(id),name,detail);
        if(myMsgService.dmlMyMsg(bean,"insert")) return "Insert Bean success!";
        return "Insert bean failed!";
    }

}
