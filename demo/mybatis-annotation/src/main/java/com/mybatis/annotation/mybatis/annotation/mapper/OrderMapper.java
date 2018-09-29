package com.mybatis.annotation.mybatis.annotation.mapper;

import com.mybatis.annotation.mybatis.annotation.bean.OrderBean;
import com.mybatis.annotation.mybatis.annotation.model.OrderModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {


    @Insert("insert into my_orders(order_id,user_id,goods_id) values(#{orderId},#{userId},#{goodsId})")
    @SelectKey(statement = "select squ_orders.nextval from dual",keyProperty = "orderId",keyColumn = "order_id",before = true,resultType = long.class)
    int insert(@Param("userId")Long userId,@Param("goodsId")Long goodsId);

    @InsertProvider(type=OrderSqlProvider.class,method = "getSqlforInsertBatch")
    @SelectKey(statement = "select squ_orders.nextval from dual",keyColumn = "order_id",keyProperty = "orderId",before = true,resultType = long.class)
    int insertForBatchByProvider(List<OrderBean> list);

    @Select("select * from my_orders where order_id=#{id}")
    @Results({
            @Result(property = "orderId",column = "order_id",id=true),
            @Result(property = "user",column = "user_id",
                    one=@One(select="com.mybatis.annotation.mybatis.annotation.mapper.UserMapper.getUserById")),
            @Result(property = "goodsSet",column = "goods_id",
                    many = @Many(select="com.mybatis.annotation.mybatis.annotation.mapper.GoodsMapper.getGoodsById"))
    })
    OrderModel getOrderById(Long id);


    @Select("select * from my_orders")
    @Results({
            @Result(property = "orderId",column = "order_id",id=true),
            @Result(property = "user",column = "user_id",
                    one=@One(select="com.mybatis.annotation.mybatis.annotation.mapper.UserMapper.getUserById")),
            @Result(property = "goodsSet",column = "goods_id",
                    many = @Many(select="com.mybatis.annotation.mybatis.annotation.mapper.GoodsMapper.getGoodsById"))
    })
    List<OrderModel> getAll();

    class OrderSqlProvider{
        public String getSqlforInsertBatch(Map<String,Object>map){
            int i=0;
            StringBuffer buf=new StringBuffer("insert into my_orders ");
            List<OrderBean>list= (List<OrderBean>) map.get("list");
            if(list!=null&&list.size()>0){
                for(OrderBean order:list){
                    i++;
                    if(i==list.size()){
                        buf.append(" select ");
                        buf.append(map.get("orderId"));
                        buf.append(",'");
                        buf.append(order.getUserId());
                        buf.append("','");
                        buf.append(order.getGoodsId());
                        buf.append("' from dual");
                    }else{
                        buf.append(" select ");
                        buf.append(map.get("orderId"));
                        buf.append(",'");
                        buf.append(order.getUserId());
                        buf.append("','");
                        buf.append(order.getGoodsId());
                        buf.append("' from dual union all ");
                    }
                }
            }
            return buf.toString();
        }
    }
}
