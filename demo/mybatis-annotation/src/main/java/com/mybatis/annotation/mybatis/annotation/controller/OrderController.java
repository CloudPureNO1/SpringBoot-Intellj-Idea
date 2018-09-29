package com.mybatis.annotation.mybatis.annotation.controller;

import com.mybatis.annotation.mybatis.annotation.model.GoodsModel;
import com.mybatis.annotation.mybatis.annotation.model.OrderModel;
import com.mybatis.annotation.mybatis.annotation.model.UserModel;
import com.mybatis.annotation.mybatis.annotation.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/my/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value="/addOrder",method = RequestMethod.GET)
    @ResponseBody
    public String addOrder(@RequestBody Map<String,Object> map){
        String str="下单成功！";
        try {
           // UserModel user= (UserModel) map.get("user");
            LinkedHashMap<String,Object>userMap= (LinkedHashMap<String, Object>) map.get("user");
            UserModel user=new UserModel();
            user.setUserName((String)userMap.get("userName"));
            user.setUserPwd((String)userMap.get("userPwd"));
        //    List<GoodsModel>goodsList= (List<GoodsModel>) map.get("goodsList");

            GoodsModel goods=null;
            List<GoodsModel>goodsList=new ArrayList<GoodsModel>();
            List<LinkedHashMap<String,Object>> list= (List<LinkedHashMap<String, Object>>) map.get("goodsList");
            if(list!=null&&list.size()>0){
                for(LinkedHashMap<String,Object> mapGoods:list){
                    goods=new GoodsModel();
                    goods.setGoodsName((String)mapGoods.get("goodsName"));
                    goods.setGoodsPrice((String)mapGoods.get("goodsPrice"));
                    goodsList.add(goods);
                }
            }
            orderService.order(user,goodsList);
        } catch (Exception e) {
            str="下单失败："+e.getMessage();
            e.printStackTrace();
        }
        return str;
    }


    @RequestMapping(value="/showOrders",method = RequestMethod.GET)
    @ResponseBody
    public String showOrders(){
      List<OrderModel> list= orderService.getOrders();
      return list.toString();
    }
}
