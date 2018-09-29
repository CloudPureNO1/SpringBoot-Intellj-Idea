package com.aop.log4j.goods.service.imp;

import com.aop.log4j.goods.Bean.GoodsBean;
import com.aop.log4j.goods.service.GoodsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsServiceImpTest {
    @Autowired
    private GoodsService goodsService;

    @Test
    public void getGoodsById() {
        goodsService.getGoodsById(0L);
    }

    @Test
    public void addGoods() {
        goodsService.addGoods(new GoodsBean());
    }
}