package com.mybatis.springbootmybatis.controller;

import com.mybatis.springbootmybatis.model.MyMsg;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.runner.Request;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;


import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class)
@SpringBootTest
public class MsgControllerTest {
    private MockMvc mockMvc;//声明 模拟MVC请求的对象

    @Autowired
    private WebApplicationContext webApplicationContext;//自动注入 WebApplicationContext 对象

    @Before
    public void setUp() throws Exception {
        //初始化模拟MVC请求的对象
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addMsg() throws Exception{
        String url="/msg/add";//设置请求的路径
        //建造 模拟请求的对象
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8).param("id","77").param("name","java").param("detail","This ia a java!");
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void addMsgByBean() throws Exception{
        //设置请求的路径
        String url="/msg/addBean";
        MyMsg bean=new MyMsg(777L,"Oracle","This is a Oracle!");
        String json=JSONObject.fromObject(bean).toString();
        //构建 模拟MVC请求的 请求对象
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
        //执行并取得MVC请求结果
        MvcResult mvcResult= mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        //取得Mvc请求结果的response对象
        MockHttpServletResponse response=mvcResult.getResponse();
        int status=response.getStatus();//结果状态 200成功
        String errorMsg=response.getErrorMessage();//错误信息
        Assert.assertEquals(errorMsg,200,status);
    }

    @Test
    public void getMsgById() throws Exception{
        String url="/msg/showMsgById";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8).param("id","1001");
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }

    @Test
    public void getAll() throws Exception{
        String url="/msg/getAll";
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8);
       MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
    }
}