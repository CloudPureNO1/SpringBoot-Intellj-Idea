package com.datasource.druid.service.imp;

import com.datasource.druid.service.MyMsgService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMsgServiceImpTest {
    @Autowired
    private MyMsgService myMsgServicee;

    @Test
    public void getMyMsgById() {
        Assert.assertEquals(10,myMsgServicee.getMyMsgById(1008L));
    }

    @Test
    public void getAll() {
        Assert.assertEquals(10,myMsgServicee.getAll());
    }
}