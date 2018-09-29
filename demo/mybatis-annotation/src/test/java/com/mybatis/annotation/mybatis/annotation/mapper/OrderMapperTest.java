package com.mybatis.annotation.mybatis.annotation.mapper;

import com.mybatis.annotation.mybatis.annotation.bean.OrderBean;
import com.mybatis.annotation.mybatis.annotation.model.GoodsModel;
import com.mybatis.annotation.mybatis.annotation.model.OrderModel;
import com.mybatis.annotation.mybatis.annotation.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderMapperTest {

    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insert() {
        assertEquals(1,orderMapper.insert(1L,1L));
    }

    @Test
    public void insertForBatchByProvider() {
        OrderBean orderBean=null;
        List<OrderBean>list=new ArrayList<OrderBean>();
        orderBean=new OrderBean(9999L,66L);
        list.add(orderBean);
        orderBean=new OrderBean(9999L,77L);
        list.add(orderBean);
        orderBean=new OrderBean(10000L,1003L);
        list.add(orderBean);
        orderBean=new OrderBean(10000L,1022L);
        list.add(orderBean);
        assertEquals(4,orderMapper.insertForBatchByProvider(list));
    }

    @Test
    public void getOrderById() {

       OrderModel order= orderMapper.getOrderById(4L);
       UserModel user=order.getUser();
       Set<GoodsModel> goodsSet=order.getGoodsSet();

        assertEquals("wangxy",orderMapper.getOrderById(4L).getUser().getUserName());
    }

    @Test
    public void getAll() {
        List<OrderModel>list=orderMapper.getAll();
        String str=">>>>>>>>>>>";
        if(list!=null&&list.size()>0){
            for(OrderModel order:list){
                if(order!=null){
                    str+="orderId="+order.getOrderId();
                    UserModel user=order.getUser();
                    if(user!=null) str+="user="+user.toString();
                    Set<GoodsModel>goodsSet=order.getGoodsSet();
                    Iterator<GoodsModel> it=goodsSet.iterator();
                    while(it.hasNext()){
                        GoodsModel goods=it.next();
                        if(goods!=null)  str+="goods="+goods.toString();
                    }
                }
            }
        }
        System.out.println(str);
    }
}