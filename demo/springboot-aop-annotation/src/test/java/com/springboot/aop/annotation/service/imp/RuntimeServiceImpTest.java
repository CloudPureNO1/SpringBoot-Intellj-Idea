package com.springboot.aop.annotation.service.imp;

import com.springboot.aop.annotation.service.RuntimeService;
import com.sun.tracing.dtrace.Attributes;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RuntimeServiceImpTest {
    @Autowired
    private RuntimeService runtimeService;

    @Test
    public void test(){
        runtimeService.showMsg("Hello");
    }
    @Test
    public void test2(){
        runtimeService.showMsg2("Hello");
    }
}