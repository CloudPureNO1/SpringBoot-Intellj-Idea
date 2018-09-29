package com.unit.test.unittest.dao.imp;

import com.unit.test.unittest.bean.MyMsgBean;
import com.unit.test.unittest.dao.IMyMsgDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMsgDaoImpTest {
    @Autowired
    IMyMsgDao dao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void add() {
        MyMsgBean bean=new MyMsgBean(1002L,"Spring Boot Learnning","This is a test for Spring Boot Learnnig!");
       assertEquals(1,dao.add(bean));
    }

    @Test
    public void del() {
        MyMsgBean bean=new MyMsgBean(1002L,"Spring Boot Learnning","This is a test for Spring Boot Learnnig!");
        assertEquals(1,dao.del(bean.getMsgId()));
    }

    @Test
    public void chg() {
        MyMsgBean bean=new MyMsgBean(1001L,"Spring Boot Learnning","This is a test for Spring Boot Learnnig!");
        assertEquals(1,dao.chg(bean));
    }

    @Test
    public void findBeanById() {
        MyMsgBean bean=new MyMsgBean(1001L,"Spring Boot","This is a test for Spring Boot!");
       assertEquals("wangsm",dao.findBeanById(1L).getMsgName());
    }

    @Test
    public void findAll() {
       // MyMsgBean bean=new MyMsgBean(1001L,"Spring Boot","This is a test for Spring Boot!");
        assertEquals(4,dao.findAll().size());
    }
}