package com.springboot.redis.db.cache.service.imp;

import com.springboot.redis.db.cache.mapper.MyMsgMapper;
import com.springboot.redis.db.cache.model.MyMsg;
import com.springboot.redis.db.cache.service.MsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class MsgServiceImp implements MsgService {
    @Autowired
    private MyMsgMapper myMsgMapper;

    @Override
   @Cacheable(cacheNames = "msg",keyGenerator ="getRedisKeyGenerator")
//@Cacheable(cacheNames = "user1")
    public MyMsg getMsgById(Long id) {
        System.out.println("****************************没有缓存的时候调用这里》》》》》》》》》》》》》》》》》");
        return myMsgMapper.getMsgById(id);
    }

    @Override
    @Cacheable(cacheNames="msg1",key="#p0")
    public MyMsg getMsg(Long id){
        System.out.println("****************************没有缓存的时候调用这里》》》》》》》》》》》》》》》》》");
        return myMsgMapper.getMsgById(id);
    }

    @Override
    @Cacheable(cacheNames = "msgDetail",keyGenerator = "getRedisKeyGenerator")
    public String getMsgDetail(Long id) {
        System.out.println("****************************没有缓存的时候调用这里》》》》》》》》》》》》》》》》》");
        return myMsgMapper.getMsgById(id).toString();
    }
}
