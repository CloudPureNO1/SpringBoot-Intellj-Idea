package com.springboot.redis.db.cache;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisDbCacheApplicationTests {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
        stringRedisTemplate.opsForValue().set("userName","wangsm");
        Assert.assertEquals("wangsm",stringRedisTemplate.opsForValue().get("userName"));


    }

    @Test
    public void showMsg(){
        try {
            System.out.println(">>>>>>包名>>>>>>>>"+ this.getClass().getPackage().getName());
            System.out.println(">>>>>>类名>>>>>>>>"+ this.getClass().getName());
            System.out.println(">>>>>>类名不含包名>>>>>>>>"+this.getClass().getName().substring(this.getClass().getName().lastIndexOf(".")));
            System.out.println(">>>>>>>getReturnType>>>>>>"+this.getClass().getMethod("showMsg").getReturnType().getName());
            System.out.println(">>>>>>>getGenericReturnType>>>>>>"+this.getClass().getMethod("showMsg").getGenericReturnType().getTypeName());
            System.out.println(">>>>>>>方法名>>>>>>"+this.getClass().getMethod("showMsg").getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
