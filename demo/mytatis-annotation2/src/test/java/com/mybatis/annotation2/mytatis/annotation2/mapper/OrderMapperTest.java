package com.mybatis.annotation2.mytatis.annotation2.mapper;

import com.mybatis.annotation2.mytatis.annotation2.bean.OrderBean;
import com.mybatis.annotation2.mytatis.annotation2.model.GoodsModel;
import com.mybatis.annotation2.mytatis.annotation2.model.UserModel;
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
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;


    @Test
    public void getAll() {

    }

    @Test
    public void insert() {
        OrderBean bean=new OrderBean(1L,1L);
        orderMapper.insert(bean);
        assertEquals(1,bean.getOrderId()==null?0L:bean.getOrderId());
        /**
         * java.lang.AssertionError:
         * Expected :1
         * Actual   :11
         *  <Click to see difference>
         */
    }

    @Test
    public void insertByProvicer() {
        List<OrderBean> beanList=new ArrayList<OrderBean>();
        OrderBean bean=new OrderBean(1L,1L);
        beanList.add(bean);
        bean=new OrderBean(2L,2L);
        beanList.add(bean);
        bean=new OrderBean(3L,3L);
        beanList.add(bean);
        assertEquals(3,orderMapper.insertByProvicer(beanList));
    }
}