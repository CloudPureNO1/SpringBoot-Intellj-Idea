package com.mybatis.annotation.mybatis.annotation.mapper;

import com.mybatis.annotation.mybatis.annotation.model.GoodsModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class GoodsMapperTest {

    @Autowired
    private  GoodsMapper goodsMapper;

    @Test
    public void insert() {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("goodsId",1L);
        map.put("goodsName","V9");
        map.put("goodsPrice","￥2000");
        assertEquals(1,goodsMapper.insert(map));
        assertEquals(9999,map.get("goodsId"));

    }   @Test
    public void insertByModel() {
        GoodsModel goods=new GoodsModel(1L,"iphneX","$1500");
        assertEquals(1,goodsMapper.insertByModel(goods));
     //   assertEquals(9999, java.util.Optional.ofNullable(goods.getGoodsId()));

    }

    @Test
    public void insertForBatch() {
        List<GoodsModel> list=new ArrayList<GoodsModel>();
        GoodsModel goods=new GoodsModel(1L,"ThinkPad E450","￥3000");
        list.add(goods);
        goods=new GoodsModel(1L,"ThinkPad G50","￥4000");
        list.add(goods);

        assertEquals(2,goodsMapper.insertForBatch(list));
    }

    @Test
    public void insertForBatchByProvider() {
        List<GoodsModel> list=new ArrayList<GoodsModel>();
        GoodsModel goods=new GoodsModel(1L,"R9","￥2999");
        list.add(goods);
        goods=new GoodsModel(1L,"VIVO9","￥3000");
        list.add(goods);

       assertEquals(2,goodsMapper.insertForBatchByProvider(list));

       for(GoodsModel gd:list){
           System.out.println(">>>>>>>>>>>"+gd.getGoodsId());
       }
    }

    @Test
    public void getGoodsById() {
       assertEquals("SpringBoot",goodsMapper.getGoodsById(77L).getGoodsName());
    }

    @Test
    public void getAll() {
    }
}