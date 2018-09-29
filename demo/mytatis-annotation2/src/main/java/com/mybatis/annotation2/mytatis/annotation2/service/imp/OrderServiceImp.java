package com.mybatis.annotation2.mytatis.annotation2.service.imp;

import com.mybatis.annotation2.mytatis.annotation2.bean.OrderBean;
import com.mybatis.annotation2.mytatis.annotation2.mapper.GoodsMapper;
import com.mybatis.annotation2.mytatis.annotation2.mapper.OrderMapper;
import com.mybatis.annotation2.mytatis.annotation2.mapper.UserMapper;
import com.mybatis.annotation2.mytatis.annotation2.model.GoodsModel;
import com.mybatis.annotation2.mytatis.annotation2.model.OrderModel;
import com.mybatis.annotation2.mytatis.annotation2.model.UserModel;
import com.mybatis.annotation2.mytatis.annotation2.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrderMapper orderMapper;

    /**
     * 此处，可以才返回false 时，抛出自定义异常，所以，可能，要把整个用Exception 包起来，然后抛出自定义异常
     * @param user
     * @param goodsList
     * @return
     */
    @Transactional  //事务管理，在启动类上添加 @@EnableTransactionManagement
    @Override
    public boolean userOrder(UserModel user, List<GoodsModel> goodsList) {
        int i=userMapper.insertByModel(user);
        if(i!=1) return false;
        if(goodsList!=null&&goodsList.size()>0){
            List<OrderBean>beanList=new ArrayList<OrderBean>();
            for(GoodsModel goods:goodsList){
                 int j=goodsMapper.insert(goods);//批量无法返回id
                 if(j==1){
                     OrderBean bean=new OrderBean();
                     bean.setUserId(user.getUserId());
                     bean.setGoodsId(goods.getGoodsId());
                     beanList.add(bean);
                 }else{
                     return false;
                 }
            }
            int k=orderMapper.insertByProvicer(beanList);
            if(k!=goodsList.size()) return false;
        }
        return true;
    }

    @Override
    public List<OrderModel> getOrderList() {
        return orderMapper.getAll();
    }
}
