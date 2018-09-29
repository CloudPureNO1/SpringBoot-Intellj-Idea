package com.mybatis.annotation2.mytatis.annotation2.controller;

import com.mybatis.annotation2.mytatis.annotation2.model.GoodsModel;
import com.mybatis.annotation2.mytatis.annotation2.model.OrderModel;
import com.mybatis.annotation2.mytatis.annotation2.model.UserModel;
import com.mybatis.annotation2.mytatis.annotation2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/order")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @ResponseBody
    @RequestMapping(value="/addOrder",method = RequestMethod.GET)
    public String addOrder(@RequestBody Map<String,Object> map){
/*        UserModel user= (UserModel) map.get("user");
        List<GoodsModel> goodsList= (List<GoodsModel>) map.get("goodsList");*/

        UserModel user=new UserModel();
        LinkedHashMap<String,Object>userMap= (LinkedHashMap<String, Object>) map.get("user");
        user.setUserName((String) userMap.get("userName"));
        user.setUserPwd((String) userMap.get("userPwd"));

        List<GoodsModel>goodsList=new ArrayList<GoodsModel>();
        GoodsModel goods=null;
        List<LinkedHashMap<String,Object>>list= (List<LinkedHashMap<String, Object>>) map.get("goodsList");
        if(list!=null&&list.size()>0){
            for(LinkedHashMap<String,Object>mapGoods:list){
                goods=new GoodsModel();
                goods.setGoodsName((String) mapGoods.get("goodsName"));
                goods.setGoodsPrice((String) mapGoods.get("goodsPrice"));
                goodsList.add(goods);
            }
        }
        if(orderService.userOrder(user,goodsList)) return "下单成功！";
        return "下单失败！";
    }


    @ResponseBody
    @RequestMapping(value="/showOrderMsg",method = RequestMethod.GET)
    public String showOrderMsg(){
        List<OrderModel>orderList= orderService.getOrderList();
        return orderList.toString();
    }


   /**以下方式是错误的。原因是request的content-body是以流的形式进行读取的，读取完一次后，便无法再次读取了。
    public String addOrderByBean(@RequestBody UserModel user , @RequestBody  List<GoodsModel>goodsList){
    解决方法：1、Map  2、controller 中建一个内部类
     * 1、Map
    public String addOrderByBean(@RequestBody Map<String,Object>Map){*/
    @ResponseBody
    @RequestMapping(value="/addOrderByBean",method = RequestMethod.POST)
    public String addOrderByBean(@RequestBody JSONParams jsonParams){
        List<GoodsModel>goodsList=jsonParams.goodsList;
        UserModel user=jsonParams.user;
        if(orderService.userOrder(user,goodsList)) return "下单成功！";
        return "下单失败！";
    }

    /**
     * 类中属性必须为public，或者有setter和getter；Param类中的属性只能比json中的属性多，不能少。
     */
    static class JSONParams{
        public UserModel user;
        public List<GoodsModel>goodsList;


    }
}
