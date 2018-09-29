package com.aop.log4j.controller;

import com.aop.log4j.user.bean.UserBean;
import net.sf.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthorityControllerTest {
    //自动注册web上下问WebApplicationContext
    @Autowired
    private WebApplicationContext webApplicationContext;

    //声明模拟mvc请求的 MockMvc对象
    private MockMvc mockMvc;

    //声明模拟mvc请求的session对象
    private MockHttpSession session;

    @Before
    public void before()  {//开始方法中进行初始化
        //初始化模拟mvc请求
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        session =new MockHttpSession();
        session.setAttribute("userName","test");
    }

    @Test
    public void showMsg() throws Exception {
        //请求路径
        String url="/authority/showMsg";
        //设置参数
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("earth","earth");
        map.put("pluto","pluto");
        JSONObject json=new JSONObject();
        json.put("map",map);
        json.put("user",new UserBean(1L,"test","test123"));
        json.put("msg","SuperMan");
        json.put("strings",new String[]{"123","wangsm"});
        json.put("ints",new int []{123,231,345,45});
        //构建RequestBildder  用MockMvcRequestBuilders构建get 请求对象 RequestBuilder 对象
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json.toString()).session(session);
        //用模拟mvc对象发起构建的request请求
        MvcResult result=mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response=result.getResponse();
        int status=response.getStatus();
        String errormsg=response.getErrorMessage();
        Assert.assertEquals(errormsg,200,status);
    }
}