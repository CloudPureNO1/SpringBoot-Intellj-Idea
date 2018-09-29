package com.mybatis.annotation2.mytatis.annotation2.mapper;

import com.mybatis.annotation2.mytatis.annotation2.bean.OrderBean;
import com.mybatis.annotation2.mytatis.annotation2.model.OrderModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {

    @Select("select * from my_orders")
    @Results({
            @Result(property = "orderId", column = "order_id", id = true),
            @Result(property = "user",//对象中的属性：OrderModel 中的 UserModel user 属性（一对一），根据一个orderId，去的人一个user
                    column = "user_id",
                    one = @One(select = "com.mybatis.annotation2.mytatis.annotation2.mapper.UserMapper.getUserById")),
            @Result(property = "goodsSet",//OrderModel 钟的Set<GoodsModel> goodsSet 属性 本质上是多对多，但是订单唯一确认时（一对多），根据一个订单id，取得多个商品
                    column = "goods_id",
                    many = @Many(select = "com.mybatis.annotation2.mytatis.annotation2.mapper.GoodsMapper.getGoodsById"))
    })
    List<OrderModel> getAll();


    /**
     * 基本插入order
     *
     * @Param OrderBean(订单插入的辅助bean)
     * @Return int
     */
    @Insert("insert into my_orders (order_id,user_id,goods_id) values(#{orderId},#{userId}，#{goodsId})")
    @SelectKey(statement = "select squ_orders.nextval from dual", keyProperty = "orderId", keyColumn = "order_id", before = true, resultType = long.class)
    int insert(OrderBean orderBean);

    @InsertProvider(type = OrderProvider.class, method = "getOrderSql")
    @SelectKey(statement = "select squ_orders.nextval from dual", keyProperty = "orderId", keyColumn = "order_id", before = true, resultType = long.class)
    int insertByProvicer(List<OrderBean> list);


    class OrderProvider {
        public String getOrderSql(Map<String, Object> map) {   //map: [orderId,collection,list]
            int i = 0;
            StringBuffer buf = new StringBuffer("insert into my_orders(order_id,user_id,goods_id)");
            List<OrderBean> list = (List<OrderBean>) map.get("list");
            Long orderId = (Long) map.get("orderId");
            if (list != null && list.size() > 0) {
                for(OrderBean bean:list){
                    i++;
                    if(i==list.size()){
                        buf=rebackSql(buf,bean,orderId," from dual");
                    }else{
                        buf=rebackSql(buf,bean,orderId," from dual union all");
                    }
                }
            }
            return buf.toString();
        }


        public StringBuffer rebackSql(StringBuffer buf,OrderBean bean, Long orderId,String str) {
                    buf.append(" select ");
                    buf.append(orderId);
                    buf.append(",");
                    buf.append(bean.getUserId());
                    buf.append(",");
                    buf.append(bean.getGoodsId());
                    buf.append(" ");
                    buf.append(str);
                    return buf;
            }

    }
}
