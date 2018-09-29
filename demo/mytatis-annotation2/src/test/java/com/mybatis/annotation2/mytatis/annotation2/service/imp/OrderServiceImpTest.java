package com.mybatis.annotation2.mytatis.annotation2.service.imp;

import com.mybatis.annotation2.mytatis.annotation2.model.GoodsModel;
import com.mybatis.annotation2.mytatis.annotation2.model.OrderModel;
import com.mybatis.annotation2.mytatis.annotation2.model.UserModel;
import com.mybatis.annotation2.mytatis.annotation2.service.OrderService;
import org.apache.ibatis.annotations.SelectProvider;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class OrderServiceImpTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void userOrder() {
        UserModel user=new UserModel();
        user.setUserName("wangsm");
        user.setUserPwd("wangsm777");

        List<GoodsModel> goodsList=new ArrayList<GoodsModel>();
        GoodsModel goods=new GoodsModel();
        goods.setGoodsName("SpringBoot 系列教程");
        goods.setGoodsPrice("$20");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("SpringBoot 入门教程");
        goods.setGoodsPrice("$20");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("SpringBoot 高级教程");
        goods.setGoodsPrice("$20");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("SpringBoot 深入浅出");
        goods.setGoodsPrice("$20");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("SpringBoot 实战教程");
        goods.setGoodsPrice("$20");
        goodsList.add(goods);

        assertEquals(true,orderService.userOrder(user,goodsList));
    }

    @Test
    public  void getOrderList(){
        UserModel user=null;
        Set<GoodsModel>goodsSet=null;
        Iterator<GoodsModel> it=null;
        GoodsModel goods=null;
        List<OrderModel>list=orderService.getOrderList();
        if(list!=null&&list.size()>0){
            for(OrderModel order:list){
                 user=order.getUser();
                 goodsSet=order.getGoodsSet();
                 it=goodsSet.iterator();
                while(it.hasNext()){
                    goods=it.next();
                    System.out.println(">>>>>>>>>>>"+order.getOrderId()+">>>>>>"+user.getUserId()+">>>>>>"+goods.getGoodsId()+">>>"+user.getUserName()+">>>"+goods.getGoodsName());
                    /**
                     * >>>>>>>>>>>16>>>>>>10022>>>>>>10051>>>wangsm>>>SpringBoot 系列教程
                     * >>>>>>>>>>>16>>>>>>10022>>>>>>10052>>>wangsm>>>SpringBoot 入门教程
                     * >>>>>>>>>>>16>>>>>>10022>>>>>>10053>>>wangsm>>>SpringBoot 高级教程
                     * >>>>>>>>>>>16>>>>>>10022>>>>>>10054>>>wangsm>>>SpringBoot 深入浅出
                     * >>>>>>>>>>>16>>>>>>10022>>>>>>10055>>>wangsm>>>SpringBoot 实战教程
                     */


                }
            }
        }
    }
}