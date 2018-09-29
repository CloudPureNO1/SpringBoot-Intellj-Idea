package com.springboot.aop.annotation.service.imp;

import com.springboot.aop.annotation.service.AllClassRunTimeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AllClassRunTimeServiceImpTest {

    @Autowired
    private AllClassRunTimeService allClassRunTimeService;
    @Test
    public void login() {
        allClassRunTimeService.login("wangsm","wangsm777");
    }

    @Test
    public void regist() {
        allClassRunTimeService.regist("wangxy","wangxy777");
    }

    @Test
    public void test(){
        allClassRunTimeService.regist("wsm","wxm777");
        allClassRunTimeService.login("wxy","wxy777");
    }
}