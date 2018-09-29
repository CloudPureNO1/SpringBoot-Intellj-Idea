package com.mybatis.annotation.mybatis.annotation.service.imp;

import com.mybatis.annotation.mybatis.annotation.model.GoodsModel;
import com.mybatis.annotation.mybatis.annotation.model.UserModel;
import com.mybatis.annotation.mybatis.annotation.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImpTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void order() {
        UserModel user=new UserModel();
        user.setUserName("wangsm");
        user.setUserPwd("wangsm777");

        List<GoodsModel>goodsList=new ArrayList<GoodsModel>();
        GoodsModel goods=new GoodsModel();
        goods.setGoodsName("iPhone X");
        goods.setGoodsPrice("$2500");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("R9");
        goods.setGoodsPrice("$500");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("V9");
        goods.setGoodsPrice("$500");
        goodsList.add(goods);

        try {
            orderService.order(user,goodsList);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>发生异常："+e.getMessage());
            e.printStackTrace();
        }

    }


    @Test
    public void orderExceptionTest() {
        UserModel user=new UserModel();
        user.setUserName("Fly");
        user.setUserPwd("Fly777");

        List<GoodsModel>goodsList=new ArrayList<GoodsModel>();
        GoodsModel goods=new GoodsModel();
        goods.setGoodsName("java");
        goods.setGoodsPrice("$500");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("spring boot");
        goods.setGoodsPrice("$550");
        goodsList.add(goods);

        try {
            orderService.orderExceptionTest(user,goodsList);
        } catch (Exception e) {
            System.out.println(">>>>>>>>>>>>发生异常："+e.getMessage());
            e.printStackTrace();
        }

    }
}