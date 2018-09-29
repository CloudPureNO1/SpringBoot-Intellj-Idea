package com.aop.log4j.controller;

import com.aop.log4j.annotation.AuthorityOnMethodAnnotation;
import com.aop.log4j.annotation.MethodRuntimeAnnotation;
import com.aop.log4j.user.bean.UserBean;
import com.aop.log4j.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/authority")
public class AuthorityController {
    @Autowired
    private UserService userService;


    @MethodRuntimeAnnotation(runtimeValue = 1L) //方法运行时间
    @AuthorityOnMethodAnnotation(authorityGroup = "admin",authoritySigle = "showMsg",authorityFlag = "and")//方法独有权限
    @RequestMapping(value="/showMsg",method = RequestMethod.POST)
    @ResponseBody
    public String showMsg(@RequestBody Map<String,Object> mapParams){
        Map<String,Object>userMap= (Map<String, Object>) mapParams.get("user");
       // Map<String,Object>strMap= (Map<String, Object>) mapParams.get("msg");
        Map<String,Object>map= (Map<String, Object>) mapParams.get("map");
        List<String> strList= (List<String>) mapParams.get("strings");
        List<Integer>intList= (List<Integer>) mapParams.get("ints");

        UserBean user=new UserBean();
        user.setUserName((String) userMap.get("userName"));
        user.setUserPwd((String)userMap.get("userPwd"));

        String [] strings=null;
        if(strList!=null&&strList.size()>0){
            strings=new String[strList.size()];
            for(int i=0;i<strList.size();i++){
                strings[i]=strList.get(i);
            }
        }
        int [] ints=null;
        if(intList!=null&&intList.size()>0){
            ints=new int[intList.size()];
            for(int i=0;i<intList.size();i++){
                ints[i]=intList.get(i);
            }
        }
        userService.showMsg(user,(String) mapParams.get("msg"),map, strings,ints);



        return "success";
    }
}
