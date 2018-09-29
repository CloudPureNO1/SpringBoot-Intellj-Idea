package com.demo.springboot.aop.service;

import org.aspectj.lang.annotation.Around;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AspectAopServiceTest {

    @Autowired
    private AspectAopService aspectAopService;
    @Test
    public void showMsg() {
        aspectAopService.showMsg("Hello Aop!");
    }

    @Test
    public void getValue() {
        aspectAopService.getValue(10);
    }

    @Test
    public void setValue() {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userName","wangsm");
        map.put("msg","this is a engenner");
        aspectAopService.setValue(map);
    }
}