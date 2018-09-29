package com.springboot.redis.service.imp;

import com.springboot.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisServiceImp {
    @Autowired
    private RedisTemplate redisTemplate;



}
