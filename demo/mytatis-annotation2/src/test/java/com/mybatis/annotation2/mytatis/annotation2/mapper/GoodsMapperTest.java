package com.mybatis.annotation2.mytatis.annotation2.mapper;

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
public class GoodsMapperTest {

    @Autowired
    private GoodsMapper goodsMapper;
    @Test
    public void insert() {
        GoodsModel goods=new GoodsModel();
        goods.setGoodsName("Spring 系列教程");
        goods.setGoodsPrice("$20");

        goodsMapper.insert(goods);

        System.out.println(">>>>>>>>>>>>>>>>>>"+goods.getGoodsId());//>>>>>>>>>>>>>>>>>>10040
        assertEquals("null",goods.getGoodsId());
        /**
         * java.lang.AssertionError:
         * Expected :null
         * Actual   :10040
         *  <Click to see difference>
         */
    }

    @Test
    public void insertBatchByScript() {
        List<GoodsModel> goodsList=new ArrayList<GoodsModel>();
        GoodsModel goods=new GoodsModel();
        goods.setGoodsName("Spring 入门教程");
        goods.setGoodsPrice("$20");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("Spring 高级教程");
        goods.setGoodsPrice("$20");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("Spring 深入浅出");
        goods.setGoodsPrice("$20");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("Spring 实战教程");
        goods.setGoodsPrice("$20");
        goodsList.add(goods);

        goodsMapper.insertBatchByScript(goodsList);

        List<GoodsModel>list=goodsMapper.getAll();
        if(list!=null&&list.size()>0){
            for(GoodsModel gd:list){
                System.out.println(">>>>>>>>>>>>>>>>>>"+gd.getGoodsId());
            }
        }
    }

    @Test
    public void insertBatchByProvicer() {
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

        goodsMapper.insertBatchByScript(goodsList);

        List<GoodsModel>list=goodsMapper.getAll();
        if(list!=null&&list.size()>0){
            for(GoodsModel gd:list){
                System.out.println(">>>>>>>>>>>>>>>>>>"+gd.getGoodsId());
            }
        }
    }
}