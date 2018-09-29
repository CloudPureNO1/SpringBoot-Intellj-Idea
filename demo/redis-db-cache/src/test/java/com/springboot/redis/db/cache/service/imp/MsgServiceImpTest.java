package com.springboot.redis.db.cache.service.imp;

import com.springboot.redis.db.cache.model.MyMsg;
import com.springboot.redis.db.cache.service.MsgService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgServiceImpTest {
    @Autowired
    private MsgService msgService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void getMsgById() {
        System.out.println(msgService.getMsgById(1008L).toString());


        System.out.println(redisTemplate.opsForValue().get("user1::1008"));
        Assert.assertEquals(true, redisTemplate.hasKey("#user1"));

  }

  @Test
  public void getMsg() {
        System.out.println(msgService.getMsg(1008L).toString());

/*
        System.out.println(redisTemplate.opsForValue().get("msg::1008"));
        Assert.assertEquals(true, redisTemplate.hasKey("#msg"));*/

  }

  @Test
  public void getRedisKeyGenerator(){
        System.out.println(">>>>>>>>"+msgService.getMsgDetail(1008L));
  }
}