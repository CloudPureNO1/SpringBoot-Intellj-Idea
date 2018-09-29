package com.mybatis.annotation2.mytatis.annotation2.mapper;

import com.mybatis.annotation2.mytatis.annotation2.model.GoodsModel;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

@Mapper
public interface GoodsMapper {
    @Select("select * from my_goods")
    @Results({
            @Result(property = "goodsId",column = "goods_id",id=true),
            @Result(property = "goodsName",column = "goods_name"),
            @Result(property = "goodsPrice",column = "goods_price")
    })
    List<GoodsModel> getAll();

    @Select("select * from my_goods where goods_id=#{id}")
    @Results({
            @Result(property = "goodsId",column = "goods_id",id=true),
            @Result(property = "goodsName",column = "goods_name"),
            @Result(property = "goodsPrice",column = "goods_price")
    })
    List<GoodsModel> getGoodsById(Long id);

    @Insert("insert into my_goods(goods_id,goods_name,goods_price) values(#{goodsId},#{goodsName},#{goodsPrice})")
    @SelectKey(statement = "select squ_goods.nextval from dual",keyProperty = "goodsId",keyColumn = "goods_id",before = true,resultType = long.class)
    int insert(GoodsModel goods);

    @Insert("<script>"
                +"insert into my_goods(goods_id,goods_name,goods_price)"
                +"<foreach collection=\"list\" item=\"goodsBean\"  separator=\"union all\"   index=\"i\">"
                +"select #{goodsId},#{goodsBean.goodsName},#{goodsBean.goodsPrice} from dual"
                +"</foreach>"
            +"</script>")
    @SelectKey(statement = "select squ_goods.nextval from dual",keyProperty = "goodsId",keyColumn = "goods_id",before = true,resultType = long.class)
    int insertBatchByScript(List<GoodsModel> goodsList);


    @InsertProvider(type=GoodsProvider.class,method = "getGoodsSql")
    @SelectKey(statement = "select squ_goods.nextval from dual",keyColumn = "goods_id",keyProperty = "goodsId",before = true,resultType = long.class)
    int insertBatchByProvicer(List<GoodsModel>goodsList);


    /**
     * 内部类，拼接动态sql
     */
    class GoodsProvider{
        public String getGoodsSql(Map<String,Object> map){   //此处map:[goodsId,collection,list]
            int i=0;//计数，用于判断是否为最后一条记录
            StringBuffer buf=new StringBuffer(" insert into my_goods(goods_id,goods_name,goods_price) ");
            List<GoodsModel>goodsList= (List<GoodsModel>) map.get("list");
            Long goodsId= (Long) map.get("goodsId");//@SelectKey 查询生成赋值的id
            if(goodsList!=null&&goodsList.size()>0){
                for(GoodsModel goods:goodsList){
                    i++;
                    if(i==goodsList.size()){
                        setSql(buf, goodsId, goods, "' from dual");
                    }else{
                        setSql(buf, goodsId, goods, "' from dual union all");
                    }
                }
            }
           return buf.toString();
        }

        public void setSql(StringBuffer buf, Long goodsId, GoodsModel goods, String s) {
            buf.append(" select to_number(");
            buf.append(goodsId);
            buf.append("),'");
            buf.append(goods.getGoodsName());
            buf.append("','");
            buf.append(goods.getGoodsPrice());
            buf.append(s);
        }
    }
}
