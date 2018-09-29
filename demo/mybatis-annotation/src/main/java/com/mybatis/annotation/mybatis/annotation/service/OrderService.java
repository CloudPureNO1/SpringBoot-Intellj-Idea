package com.mybatis.annotation.mybatis.annotation.service;

import com.mybatis.annotation.mybatis.annotation.model.GoodsModel;
import com.mybatis.annotation.mybatis.annotation.model.OrderModel;
import com.mybatis.annotation.mybatis.annotation.model.UserModel;

import java.util.List;

public interface OrderService {
    void order(UserModel user, List<GoodsModel> goodsList)  throws Exception;
    void orderExceptionTest(UserModel user, List<GoodsModel> goodsList)  throws Exception;

    List<OrderModel>  getOrders();
}
