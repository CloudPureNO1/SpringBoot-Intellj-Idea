package com.datasource.druid.mapper;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMsgMapperTest {
    @Autowired
    private MyMsgMapper myMsgMapper;

    @Test
    public void getMyMsgById() {
        Assert.assertEquals("",myMsgMapper.getMyMsgById(1008L).toString());
    }

    @Test
    public void getAll(){
        Assert.assertEquals(2,myMsgMapper.getAll().size());
    }
}