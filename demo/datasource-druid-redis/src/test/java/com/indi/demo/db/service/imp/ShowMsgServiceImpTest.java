package com.indi.demo.db.service.imp;

import com.indi.demo.db.service.ShowMsgService;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;
import java.util.Set;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class ShowMsgServiceImpTest {
    private static final  Logger logger=Logger.getLogger(ShowMsgServiceImpTest.class);
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplatee;
    @Autowired
    private ShowMsgService showMsgService;
    @Test
    public void showMsg() {
        logger.info(">>>>>>>1008>>>msg2:"+showMsgService.showMsg(1008L,"AAE211").toString());
        redisTemplate.opsForValue().set("msg2str",showMsgService.showMsg(1007L,"AAE211").toString());
        logger.info(">>>>>>>>>>>>>.1007>>>>>>>>>>>>");
        logger.info(">>>>>>>>>>>>>1009>>>>>>>>>>>>>>>>>"+showMsgService.showMsg(1009L,"AAE211").toString());
    }

    @Test
    public void showMsg2() {
        logger.info(">>>>>>>1008>>>msg2:"+showMsgService.showMsg2(1008L,"AAE211").toString());
        redisTemplate.opsForValue().set("msg2str",showMsgService.showMsg2(1007L,"AAE211").toString());
        logger.info(">>>>>>>>>>>>>.1007>>>>>>>>>>>>");
        logger.info(">>>>>>>>>>>>>1009>>>>>>>>>>>>>>>>>"+showMsgService.showMsg2(1009L,"AAE211").toString());

    }

    @Test
    public void removeRedis(){
       Set<String> keys=stringRedisTemplatee.keys("*");
       if(!keys.isEmpty()){
   /*         Iterator<String>iterator=keys.iterator();
            while(iterator.hasNext()){
                String key=iterator.next();
                if(redisTemplate.hasKey(key)) redisTemplate.delete(key);
            }*/
logger.info("delete>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
           stringRedisTemplatee.delete(keys);
       }
    }
}