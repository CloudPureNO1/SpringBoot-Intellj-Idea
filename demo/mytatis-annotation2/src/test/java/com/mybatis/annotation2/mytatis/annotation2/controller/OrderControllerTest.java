package com.mybatis.annotation2.mytatis.annotation2.controller;

import com.mybatis.annotation2.mytatis.annotation2.model.GoodsModel;
import com.mybatis.annotation2.mytatis.annotation2.model.UserModel;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableTransactionManagement
public class OrderControllerTest {
    //声明模拟MVC请求的对象
    private MockMvc mockMvc;

    //自动注入WebApplicationContext 对象
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() throws Exception {
        //利用MockMvcBuildsers 对象构建 模拟MVC 请求对象
        mockMvc=MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void addOrder() {
        //请求路径
        String url="/order/addOrder";
        //设置请求参数
        Map<String,Object>map=setMapParam();
        String json= JSONObject.fromObject(map).toString();
        //用MockMvcRequestBuilders构建RequestBuilder  get 请求(因为controller,中是用的get请求)
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
        try {
        //用模拟的MockMvc 对象，发起请求，得到 MVC 请求结果MvcResult
           MvcResult mvcResult= mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
           //此处支取 MockHttpServletResponse，也可以取 modelAndVeiw ,Request,接下异常等，具体看MvcResult 中的方法
            MockHttpServletResponse response=mvcResult.getResponse();
            //取得结果状态 200成功，400位失败
            int status=response.getStatus();
            //取得错误信息
            String errorMsg=response.getErrorMessage();
            //断言判断status 是否为预期的200，如果不是，显示错误信息
            assertEquals(errorMsg,200,status);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 执行成功的打印信息

         MockHttpServletRequest:
         HTTP Method = GET
         Request URI = /order/addOrder
         Parameters = {}
         Headers = {Content-Type=[application/json;charset=UTF-8]}
         Body = {"goodsList":[{"goodsId":0,"goodsName":"Mybatis 系列教程","goodsPrice":"$20"},{"goodsId":0,"goodsName":"Mybatis 入门教程","goodsPrice":"$20"},{"goodsId":0,"goodsName":"Mybatis 高级教程","goodsPrice":"$20"},{"goodsId":0,"goodsName":"Mybatis 深入浅出","goodsPrice":"$20"},{"goodsId":0,"goodsName":"Mybatis 实战教程","goodsPrice":"$20"}],"user":{"userId":0,"userName":"wangxy","userPwd":"wangxy777"}}
         Session Attrs = {}

         Handler:
         Type = com.mybatis.annotation2.mytatis.annotation2.controller.OrderController
         Method = public java.lang.String com.mybatis.annotation2.mytatis.annotation2.controller.OrderController.addOrder(java.util.Map<java.lang.String, java.lang.Object>)

         Async:
         Async started = false
         Async result = null

         Resolved Exception:
         Type = null

         ModelAndView:
         View name = null
         View = null
         Model = null

         FlashMap:
         Attributes = null

         MockHttpServletResponse:
         Status = 200
         Error message = null
         Headers = {Content-Type=[text/plain;charset=UTF-8], Content-Length=[15]}
         Content type = text/plain;charset=UTF-8
         Body = 下单成功！
         Forwarded URL = null
         Redirected URL = null
         Cookies = []

         */
    }


    @Test
    public void addOrderByBean(){
        //请求路径
        String url="/order/addOrderByBean";
        //设置请求参数
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("goodsList",setGoodsList("SpringMvc","$","100"));
        map.put("user",setUser("BlueSky","BlueSky777"));
        String json=JSONObject.fromObject(map).toString();
        //用MockMvcRequestBuilders 构建 RequestBuilder 请求对象
        RequestBuilder requestBuilder=MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON_UTF8).content(json);
        try {
        //用MVC模拟对象 mockMvc 发起请求,并得到MvcResult
           MvcResult mvcResult= mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
           // 取得 MockHttpServletResponse
          MockHttpServletResponse response=  mvcResult.getResponse();
          //取得 结果状态 200成功，400失败
            int status=response.getStatus();
            //取得 错误信息，如果结果正常，则为空
            String errorMsg=response.getErrorMessage();
            assertEquals(errorMsg,200,status);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 打印信息
         * MockHttpServletRequest:
         *       HTTP Method = POST
         *       Request URI = /order/addOrderByBean
         *        Parameters = {}
         *           Headers = {Content-Type=[application/json;charset=UTF-8]}
         *              Body = {"goodsList":[{"goodsId":0,"goodsName":"SpringMvc 系列教程","goodsPrice":"$100"},{"goodsId":0,"goodsName":"SpringMvc 入门教程","goodsPrice":"$100"},{"goodsId":0,"goodsName":"SpringMvc 高级教程","goodsPrice":"$100"},{"goodsId":0,"goodsName":"SpringMvc 深入浅出","goodsPrice":"$100"},{"goodsId":0,"goodsName":"SpringMvc 实战教程","goodsPrice":"$100"}],"user":{"userId":0,"userName":"BlueSky","userPwd":"BlueSky777"}}
         *     Session Attrs = {}
         *
         * Handler:
         *              Type = com.mybatis.annotation2.mytatis.annotation2.controller.OrderController
         *            Method = public java.lang.String com.mybatis.annotation2.mytatis.annotation2.controller.OrderController.addOrderByBean(com.mybatis.annotation2.mytatis.annotation2.controller.OrderController$JSONParams)
         *
         * Async:
         *     Async started = false
         *      Async result = null
         *
         * Resolved Exception:
         *              Type = null
         *
         * ModelAndView:
         *         View name = null
         *              View = null
         *             Model = null
         *
         * FlashMap:
         *        Attributes = null
         *
         * MockHttpServletResponse:
         *            Status = 200
         *     Error message = null
         *           Headers = {Content-Type=[text/plain;charset=UTF-8], Content-Length=[15]}
         *      Content type = text/plain;charset=UTF-8
         *              Body = 下单成功！
         *     Forwarded URL = null
         *    Redirected URL = null
         *           Cookies = []
         */
    }



    @Test
    public void showOrderMsg() {
        //请求路径
        String url="/order/showOrderMsg";
        //用MockMvcBuilders 构建 请求创建对象 RequestBuilder get 请求对象
        //因为没有参数，所以不设置content
        RequestBuilder requestBuilder=MockMvcRequestBuilders.get(url).contentType(MediaType.APPLICATION_JSON_UTF8);
        try {
        //用构建好的MVC请求对象，发起请求,得到请求的结果：MvcResult
          MvcResult mvcResult=  mockMvc.perform(requestBuilder).andDo(MockMvcResultHandlers.print()).andReturn();
          //取得 MockHttpServletResponse
            MockHttpServletResponse response=mvcResult.getResponse();
            //取得请求结果状态：200成功，400失败
            int status=response.getStatus();
            //取得错误信息
            String errorMsg=response.getErrorMessage();
            if(200==status){//成功，打印返回结果
               System.out.println(">>>>>换回结果："+response.getContentAsString());
            }else{//失败，输出错误信息
                System.out.println(">>>>>>错误信息；"+errorMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 打印信息
         * MockHttpServletRequest:
         *       HTTP Method = GET
         *       Request URI = /order/showOrderMsg
         *        Parameters = {}
         *           Headers = {Content-Type=[application/json;charset=UTF-8]}
         *              Body = null
         *     Session Attrs = {}
         *
         * Handler:
         *              Type = com.mybatis.annotation2.mytatis.annotation2.controller.OrderController
         *            Method = public java.lang.String com.mybatis.annotation2.mytatis.annotation2.controller.OrderController.showOrderMsg()
         *
         * Async:
         *     Async started = false
         *      Async result = null
         *
         * Resolved Exception:
         *              Type = null
         *
         * ModelAndView:
         *         View name = null
         *              View = null
         *             Model = null
         *
         * FlashMap:
         *        Attributes = null
         *
         * MockHttpServletResponse:
         *            Status = 200
         *     Error message = null
         *           Headers = {Content-Type=[text/plain;charset=UTF-8], Content-Length=[1855]}
         *      Content type = text/plain;charset=UTF-8
         *              Body = [OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10051, goodsName='SpringBoot 系列教程', goodsPrice='$20'}]}, OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10052, goodsName='SpringBoot 入门教程', goodsPrice='$20'}]}, OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10053, goodsName='SpringBoot 高级教程', goodsPrice='$20'}]}, OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10054, goodsName='SpringBoot 深入浅出', goodsPrice='$20'}]}, OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10055, goodsName='SpringBoot 实战教程', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10056, goodsName='Mybatis 系列教程', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10057, goodsName='Mybatis 入门教程', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10058, goodsName='Mybatis 高级教程', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10059, goodsName='Mybatis 深入浅出', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10060, goodsName='Mybatis 实战教程', goodsPrice='$20'}]}]
         *     Forwarded URL = null
         *    Redirected URL = null
         *           Cookies = []
         * >>>>>换回结果：[OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10051, goodsName='SpringBoot 系列教程', goodsPrice='$20'}]}, OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10052, goodsName='SpringBoot 入门教程', goodsPrice='$20'}]}, OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10053, goodsName='SpringBoot 高级教程', goodsPrice='$20'}]}, OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10054, goodsName='SpringBoot 深入浅出', goodsPrice='$20'}]}, OrderModel{orderId=16, user=UserModel{userId=10022, userName='wangsm', userPwd='wangsm777'}, goodsSet=[GoodsModel{goodsId=10055, goodsName='SpringBoot 实战教程', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10056, goodsName='Mybatis 系列教程', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10057, goodsName='Mybatis 入门教程', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10058, goodsName='Mybatis 高级教程', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10059, goodsName='Mybatis 深入浅出', goodsPrice='$20'}]}, OrderModel{orderId=17, user=UserModel{userId=10025, userName='wangxy', userPwd='wangxy777'}, goodsSet=[GoodsModel{goodsId=10060, goodsName='Mybatis 实战教程', goodsPrice='$20'}]}]
         */
    }



    public UserModel setUser(String userName,String userPwd){
        UserModel user=new UserModel();
        user.setUserName(userName);
        user.setUserPwd(userPwd);
        return user;
    }

    public List<GoodsModel> setGoodsList(String type,String priceType,String price){
        List<GoodsModel> goodsList=new ArrayList<GoodsModel>();
        GoodsModel goods=new GoodsModel();
        goods.setGoodsName(type+" 系列教程");
        goods.setGoodsPrice(priceType+price);
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName(type+" 入门教程");
        goods.setGoodsPrice(priceType+price);
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName(type+" 高级教程");
        goods.setGoodsPrice(priceType+price);
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName(type+" 深入浅出");
        goods.setGoodsPrice(priceType+price);
        goodsList.add(goods);

        goods=new GoodsModel();
        goods.setGoodsName(type+" 实战教程");
        goods.setGoodsPrice(priceType+price);
        goodsList.add(goods);
        return goodsList;
    }

    public Map<String,Object> setMapParam(){
        Map<String,Object>map=new HashMap<String,Object>();
        map.put("user",setUser("wanngxy","wangxy777"));
        map.put("goodsList",setGoodsList("Mybatis","$","20"));
        return map;
    }
}