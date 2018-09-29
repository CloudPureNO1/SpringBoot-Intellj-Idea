package com.aop.log4j.goods.service.imp;

import com.aop.log4j.goods.Bean.GoodsBean;
import com.aop.log4j.goods.service.GoodsService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GoodsServiceImp implements GoodsService {
    private static final Logger logger=Logger.getLogger(GoodsServiceImp.class);
    @Override
    public GoodsBean getGoodsById(Long Id) {
        logger.debug(">>>>>>>>>>>>>>DEBUG> 级别日志>>>>getGoodsById>>>>>>>>>>>>>>");
        logger.info(">>>>>>>>>>>>>>INFO> 级别日志>>>>getGoodsById>>>>>>>>>>>>>>");
        logger.warn(">>>>>>>>>>>>>>>WARN 级别日志>>>>getGoodsById>>>>>>>>>>>>>>");
        logger.error(">>>>>>>>>>>>>>ERROR> 级别日志>>>>getGoodsById>>>>>>>>>>>>>>");
        return null;
    }

    @Override
    public Boolean addGoods(GoodsBean bean) {
        logger.debug(">>>>>>>>>>>>>>DEBUG> 级别日志>>>>addGoods>>>>>>>>>>>>>>");
        logger.info(">>>>>>>>>>>>>>INFO> 级别日志>>>>addGoods>>>>>>>>>>>>>>");
        logger.warn(">>>>>>>>>>>>>>>WARN 级别日志>>>>addGoods>>>>>>>>>>>>>>");
        logger.error(">>>>>>>>>>>>>>ERROR> 级别日志>>>>addGoods>>>>>>>>>>>>>>");
        return null;
    }
}
