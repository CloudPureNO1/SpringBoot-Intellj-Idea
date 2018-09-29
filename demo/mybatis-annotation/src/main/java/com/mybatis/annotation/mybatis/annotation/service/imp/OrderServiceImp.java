package com.mybatis.annotation.mybatis.annotation.service.imp;

import com.mybatis.annotation.mybatis.annotation.bean.OrderBean;
import com.mybatis.annotation.mybatis.annotation.mapper.GoodsMapper;
import com.mybatis.annotation.mybatis.annotation.mapper.OrderMapper;
import com.mybatis.annotation.mybatis.annotation.mapper.UserMapper;
import com.mybatis.annotation.mybatis.annotation.model.GoodsModel;
import com.mybatis.annotation.mybatis.annotation.model.OrderModel;
import com.mybatis.annotation.mybatis.annotation.model.UserModel;
import com.mybatis.annotation.mybatis.annotation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;


    @Override
    @Transactional //@EnableTransactionManagement  启动类上添加
    public void order(UserModel user, List<GoodsModel> goodsList) throws Exception{
        List<OrderBean>orderBeanList=new ArrayList<OrderBean>();
        OrderBean orderBean=null;
        userMapper.insert(user);//@SelectKey 插入序列号，可以在插入后自动取得
        if(goodsList!=null&&goodsList.size()>0){
            for(GoodsModel goods:goodsList){
                goodsMapper.insertByModel(goods);////@SelectKey 插入序列号，可以在插入后自动取得
                orderBean=new OrderBean(user.getUserId(),goods.getGoodsId());
                orderBeanList.add(orderBean);
            }
        }

        orderMapper.insertForBatchByProvider(orderBeanList);

    }



    @Override
    @Transactional //@EnableTransactionManagement  启动类上添加
    public void orderExceptionTest(UserModel user, List<GoodsModel> goodsList) throws Exception{
         order(user,goodsList);
        int i=100/0;//此处异常，检查数据是否会回退
    }

    @Override
    public List<OrderModel> getOrders() {
        return orderMapper.getAll();
    }


}
