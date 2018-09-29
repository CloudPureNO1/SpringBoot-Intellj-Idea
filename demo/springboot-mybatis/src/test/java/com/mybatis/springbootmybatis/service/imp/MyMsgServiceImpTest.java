package com.mybatis.springbootmybatis.service.imp;

import com.mybatis.springbootmybatis.model.MyMsg;
import com.mybatis.springbootmybatis.service.MyMsgService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest

public class MyMsgServiceImpTest {

    @Autowired
    private MyMsgService myMsgService;
    @Test
    public void addMsg() {
        assertEquals(1,myMsgService.addMsg("1009","Mybatis","This is Mybatis!"));
    }

    @Test
    public void addMsgByBean() {
        assertEquals(1,myMsgService.addMsgByBean(new MyMsg(1008L,"mybatis","this is mybatis!")));
    }

    @Test
    public void getMsgById() {
        assertEquals("Mybatis",myMsgService.getMsgById("1009").getMsgName());
    }

    @Test
    public void getAll() {
        assertEquals(8,myMsgService.getAll().size());
    }
}