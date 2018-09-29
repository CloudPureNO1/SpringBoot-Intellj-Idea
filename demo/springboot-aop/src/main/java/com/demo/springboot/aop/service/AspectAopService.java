package com.demo.springboot.aop.service;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AspectAopService {
    protected String showMsg(String msg){
        System.out.println("--------------->"+msg);
        return msg;
    }

    protected int getValue(int i){
        i=i/0;
        return i;
    }

    protected String setValue(Map<String,Object>map){
        return map.toString();
    }
}
