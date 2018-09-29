package com.unit.test.unittest.controller;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BeanObjectControllerTest {
    private MockMvc mockMvc;//模拟Mvc请求的对象

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContext).build();//初始化MVC 模拟对象
    }

    @Test
    public void dmlBean() throws Exception{
        String url="/beanObject/dml";
        Map<String,String> map=new HashMap<String,String>();
        map.put("id","1007");
        map.put("name","spring-boot");
        map.put("detail","This is spring-boot");
        map.put("type","insert");
        String json= JSONObject.fromObject(map).toString();
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
        MvcResult mvcResult= mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }

    @Test
    public void findById() throws Exception{
        String url="/beanObject/list";
        List<Long> list=new ArrayList<Long>();
        list.add(1001L);
        list.add(1002L);
        list.add(1005L);
        list.add(1007L);
        String json=JSONArray.fromObject(list).toString();
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }

    @Test
    public void del() throws Exception{
        String url="/beanObject/del";
        Long [] ids=new Long[]{1005L,1006L,1007L,1008L};
        String json=JSONArray.fromObject(ids).toString();
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
        MvcResult mvcResult=mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response= mvcResult.getResponse();
        int status=response.getStatus();
        assertEquals(response.getErrorMessage(),200,status);
    }
}