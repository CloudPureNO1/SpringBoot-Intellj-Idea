package com.demo.springboot.aop.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AopServiceTest {

    @Autowired
    private AopService aopService;

    @Test
    public void showMsg() {
        aopService.showMsg("wangsm","Fly in the sky!");
    }

    @Test
    public void showException() {
        aopService.showException();
        aopService.showException2();
    }

    @Test
    public void showExceptionUnKown() {
        aopService.showExceptionUnKown();
    }
}