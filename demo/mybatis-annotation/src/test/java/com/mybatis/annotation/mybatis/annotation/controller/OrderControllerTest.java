package com.mybatis.annotation.mybatis.annotation.controller;

import com.mybatis.annotation.mybatis.annotation.model.GoodsModel;
import com.mybatis.annotation.mybatis.annotation.model.UserModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.setup.MockMvcConfigurer;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderControllerTest {
    private MockMvc mockMvc;//声明模拟MVC的求对象MockMVC mockMvc；

    //注入模拟的WebApplicationContext 对象
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        //用MockMvcBuidlsers 构建并初始化模拟MVC的对象mockMvc
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addOrder() {
        UserModel user=new UserModel();
        user.setUserName("Fly_Sky");
        user.setUserPwd("Fly_In_The_Sky");

        List<GoodsModel> goodsList=new ArrayList<GoodsModel>();
        GoodsModel goods=new GoodsModel();
        goods.setGoodsName("Java 深入浅出");
        goods.setGoodsPrice("￥100");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("Spring 深入浅出");
        goods.setGoodsPrice("￥150");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("Spring Boot 深入浅出");
        goods.setGoodsPrice("￥150");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("Jquery 深入浅出");
        goods.setGoodsPrice("￥100");
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName("Javascritp 深入浅出");
        goods.setGoodsPrice("￥100");
        goodsList.add(goods);

        String url="/my/order/addOrder";

        Map<String,Object> map=new HashMap<String,Object>();
        map.put("user",user);
        map.put("goodsList",goodsList);
        String json=JSONObject.fromObject(map).toString();
        //构建模拟的请求对象
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
        //发送请求
        try{

            MvcResult mvcResult=mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
           System.out.println( mvcResult.getResolvedException());
            MockHttpServletResponse response=mvcResult.getResponse();
            int status=response.getStatus();
            String errorMsg=response.getErrorMessage();
            assertEquals(errorMsg,200,status);
        }catch(Exception e){
            e.printStackTrace();
        }

    }


}