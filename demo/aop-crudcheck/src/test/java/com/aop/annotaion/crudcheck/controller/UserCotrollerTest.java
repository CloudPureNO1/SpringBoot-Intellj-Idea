package com.aop.annotaion.crudcheck.controller;

import com.aop.annotaion.crudcheck.bean.UserBean;
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

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserCotrollerTest {
    //WebApplicationContext 对象
    @Autowired
    private WebApplicationContext webApplicationContext;

    //声明一个模拟mvc的对象
    private MockMvc mockMvc;

    //传入测试用的session对象
    private MockHttpSession session;

    @Before
    public void before(){
        //初始化模拟mvc的对象,使用MockMvcBuilders构建mvc模拟对象，MockMvc mockMvc
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();

        //测试前初始化session
        session=new MockHttpSession();
/*        UserBean userBean=new UserBean("Pluto","all");
        session.setAttribute("user",userBean);*/
        session.setAttribute("userName","Pluto");//此处测试时，会用到userName

    }

    @Test
    public void testAll() throws Exception {
        //设置请求路径
        String url="/user/testAll";
        //此处测试，不传参数了

        //建构一个RequestBuilder 对象
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8).session(session);
        //通过模拟mvc对象发起请求，并得到请求结果
        MvcResult mvcResult= mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response=mvcResult.getResponse();
        int status=response.getStatus();
        String errorMsg=response.getErrorMessage();
        Assert.assertEquals(errorMsg,200,status);
    }
}