package com.aop.log4j.goods.service;

import com.aop.log4j.goods.Bean.GoodsBean;

public interface GoodsService {
    GoodsBean getGoodsById(Long Id);
    Boolean addGoods(GoodsBean bean);
}
