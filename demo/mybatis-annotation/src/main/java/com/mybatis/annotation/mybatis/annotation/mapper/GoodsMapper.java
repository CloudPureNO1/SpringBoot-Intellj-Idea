package com.mybatis.annotation.mybatis.annotation.mapper;

import com.mybatis.annotation.mybatis.annotation.model.GoodsModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper {
    @Insert("insert into my_goods (goods_id,goods_name,goods_price) values(#{goodsId},#{goodsName},#{goodsPrice})")
    @SelectKey(statement = "select squ_goods.nextval from dual",keyProperty = "goodsId",keyColumn = "goods_id",before = true,resultType = long.class)
    int insert(Map<String,Object>map);

    @Insert("insert into my_goods (goods_id,goods_name,goods_price) values(#{goodsId},#{goodsName},#{goodsPrice})")
    @SelectKey(statement = "select squ_goods.nextval from dual",keyProperty = "goodsId",keyColumn = "goods_id",before = true,resultType = long.class)
    int insertByModel(GoodsModel goodsModel);

    @Insert({"<script>" +
/*            "       <selectKey keyColumn=\"goods_id\" keyProperty=\"goodsId\" order=\"BEFORE\" resultType=\"Long\">" +
            "            <!-- 编写查询主键的SQL语句 -->" +
            "            <!-- BEFORE：-->" +
            "            SELECT squ_goods.nextval FROM dual" +
            "            <!-- AFTER:" +
            "                SELECT seq_deptno.currval FROM dual" +
            "            -->" +
            "        </selectKey>" +*/
            "        insert into my_goods(goods_id,goods_name,goods_price)" +
            "        <foreach collection=\"list\" item=\"goodsBean\"  separator=\"union all\"  index=\"i\">" +
            "          select #{goodsId}, #{goodsBean.goodsName}, #{goodsBean.goodsPrice} from dual " +
            "        </foreach></script>"})
    @SelectKey(statement = "select squ_goods.nextval from dual",keyColumn = "goods_id",keyProperty = "goodsId",before = true,resultType = long.class)
    int insertForBatch(List<GoodsModel>goodsList);

    @InsertProvider(type=Provider.class,method ="getGoodsBatchInsertSql")
    @SelectKey(statement = "select squ_goods.nextval from dual",keyColumn = "goods_id",keyProperty = "goodsId",before = true,resultType = long.class)
    int insertForBatchByProvider(List<GoodsModel>list);




    @Select("select * from my_goods where goods_id=#{goodsId}")
    @Results({
            @Result(column = "goods_id",property = "goodsId",id=true),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "goods_price",property = "goodsPrice")
     })
    GoodsModel getGoodsById(@Param("goodsId") Long id);

    @Select("select * from my_goods")
    @Results({
            @Result(column = "goods_id",property = "goodsId",id=true),
            @Result(column = "goods_name",property = "goodsName"),
            @Result(column = "goods_price",property = "goodsPrice")
       })
    List<GoodsModel>getAll();









    class Provider{
        public String getGoodsBatchInsertSql(Map<String,Object> map){
            StringBuffer buf=new StringBuffer();
            buf.append("insert into my_goods (goods_id,goods_name,goods_price)");
            List<GoodsModel>list= (List<GoodsModel>) map.get("list");
            int i=0;
            if(list!=null&&list.size()>0){
                for(GoodsModel goods:list){
                    i++;
                    if(i==list.size()){
                       // buf.append("select #{goods.goodsName},#{goods.goodsPrice} from dual");
                        setProviderSql(map, buf, goods, "' from dual");
                    }else{
                       // buf.append("select #{goods.goodsName},#{goods.goodsPrice} from dual union all");
                        setProviderSql(map, buf, goods, "' from dual union all");
                    }
                }
            }

            return buf.toString();
        }
    }

    public static void setProviderSql(Map<String, Object> map, StringBuffer buf, GoodsModel goods, String s) {
        buf.append(" select ");
        buf.append(map.get("goodsId"));
        buf.append(" , '");
        buf.append(goods.getGoodsName());
        buf.append("' , '");
        buf.append(goods.getGoodsPrice());
        buf.append(s);
    }
}
