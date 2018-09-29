package com.mybatis.annotation2.mytatis.annotation2.service;

import com.mybatis.annotation2.mytatis.annotation2.bean.OrderBean;
import com.mybatis.annotation2.mytatis.annotation2.model.GoodsModel;
import com.mybatis.annotation2.mytatis.annotation2.model.OrderModel;
import com.mybatis.annotation2.mytatis.annotation2.model.UserModel;

import java.util.List;

public interface OrderService  {
    boolean userOrder(UserModel user, List<GoodsModel>goods);
    List<OrderModel> getOrderList();
}
