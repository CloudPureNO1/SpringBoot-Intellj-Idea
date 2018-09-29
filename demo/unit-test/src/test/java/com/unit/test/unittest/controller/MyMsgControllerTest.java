package com.unit.test.unittest.controller;

import com.unit.test.unittest.bean.MyMsgBean;
import net.sf.json.JSON;
import org.json.JSONObject;
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
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyMsgControllerTest {
    @Autowired
    private WebApplicationContext webApplicationContex;


    private MockMvc mockMvc; //模拟mvc对象
   // private MockHttpSession session;//需要模拟session中的信息时用，比如已经登录的用户，session.setAttrbute('user','user');



    @Before
    public void setUp() throws Exception {
        mockMvc= MockMvcBuilders.webAppContextSetup(webApplicationContex).build();//初始化MockMvc 对象

    }

    @Test
    public void findAll()   {
        try{
            String url="/myMsg/list";
            RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8);
            MvcResult mvcResult=mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();
            int status = mvcResult.getResponse().getStatus();
            String content = mvcResult.getResponse().getContentAsString();

            Assert.assertEquals("正确",200,status);

            Assert.assertFalse("错误",status!=200);
            System.out.println("返回结果："+status);
            System.out.println(content);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void findBeanById() throws Exception {
        String url="/myMsg/1001";

        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8);
        MvcResult mvcResult= mockMvc.perform(requestBuilder).andExpect(MockMvcResultMatchers.status().isOk()).andDo(MockMvcResultHandlers.print()).andReturn();

    }

    @Test
    public void dmlMyMsg() {
        try{
            String url="/myMsg/dml";
            Map<String,String> map=new HashMap<String,String>();
            map.put("id","1005");
            map.put("name","SpringMVC");
            map.put("detail","SpringMVC!");
            map.put("type","update");
            String json= net.sf.json.JSONObject.fromObject(map).toString();


            MultiValueMap<String,String>params=new LinkedMultiValueMap<String,String>();
            params.set("id","1005");
            params.set("name","SpringMVC");
            params.set("detail","SpringMVC!");
            params.set("type","update");

            /**
             * Controller 方法中的参数，@RequestParam  请求参数要用params 或者parma 传参数，否则报找不到绑定的参数
             */
            RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8).params(params);

            /**
             *  Controller 方法中的参数，如果用的是@RequestBody   请求参数用content（json)
             */
            //  RequestBuilder requestBuilder=MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
            MvcResult mvcResult= mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
            MockHttpServletResponse response= mvcResult.getResponse();
            System.out.println(">>>>>>>>>>>>>>>>>>>>"+response.getErrorMessage());
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void addBean() throws Exception{
        String url="/myMsg/addBean";
        Map<String,String>map=new HashMap<String,String>();
        map.put("id","1006");
        map.put("name","Spring");
        map.put("detail","This is a Spring mark!");
        String json= net.sf.json.JSONObject.fromObject(map).toString();
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
        MvcResult mvcResult= mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
        MockHttpServletResponse response=mvcResult.getResponse();
       int status= response.getStatus();
       assertEquals(200,status);

    }
}