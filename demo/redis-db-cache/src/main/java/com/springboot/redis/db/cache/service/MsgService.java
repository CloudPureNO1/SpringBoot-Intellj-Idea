package com.springboot.redis.db.cache.service;

import com.springboot.redis.db.cache.model.MyMsg;

public interface MsgService {
    MyMsg getMsgById(Long id);
    public MyMsg getMsg(Long id);

    String getMsgDetail(Long id);
}
