package com.unit.test.unittest.controller;

import com.unit.test.unittest.bean.MyMsgBean;
import com.unit.test.unittest.service.MyMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.Request;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/beanObject")
public class BeanObjectController {

    @Autowired
    private MyMsgService myMsgService;

    @RequestMapping(value="/dml",method = RequestMethod.GET)
    public String dmlBean(@RequestBody Map<String,String> map){
         MyMsgBean bean=new MyMsgBean(Long.valueOf(map.get("id")),map.get("name"),map.get("detail"));
         if(myMsgService.dmlMyMsg(bean,map.get("type"))) return map.get("type")+" success!";
         return map.get("type")+" failed!";
    }

    @RequestMapping(value="/list",method = RequestMethod.GET)
    public String findById(@RequestBody List<Long> idList){
        String str="";
        if(idList!=null&&idList.size()>0){
            for(Long id:idList){
              MyMsgBean bean=myMsgService.findBeanById(id);
              str+=bean.toString();
            }
        }
        return str;
    }

    @RequestMapping(value="/del",method = RequestMethod.GET)
    public String del(@RequestBody Long [] idArys){
        int n=0;
        if(idArys!=null&&idArys.length>0){
            for(Long id:idArys){
                if(!myMsgService.dmlMyMsg(new MyMsgBean(id,"",""),"delete")){
                    return ">>>>>>执行失败,id="+id;
                }
            }
        }
        if(n!=0){
            return "执行成功条数："+n;
        }else{
            return "全部执行失败";
        }
    }
}
