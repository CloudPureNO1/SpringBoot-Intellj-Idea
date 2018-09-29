package com.unit.test.unittest.service.imp;

import com.unit.test.unittest.bean.MyMsgBean;
import com.unit.test.unittest.service.MyMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMsgServiceImpTest {
    @Autowired
    MyMsgService service;

    @Test
    public void dmlMyMsg() {
        MyMsgBean bean=new MyMsgBean(1003L,"SpringBoot","This is a Spring Boot!");
        assertEquals(true,service.dmlMyMsg(bean,"delete"));
    }

    @Test
    public void findBeanById() {
        assertEquals("Spring Boot Learnning",service.findBeanById(1001L).getMsgName());
    }

    @Test
    public void findAll() {
        assertEquals(4,service.findAll().size());
    }
}