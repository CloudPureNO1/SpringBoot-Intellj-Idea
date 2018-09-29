package com.aop.log4j.goods.Bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class GoodsBean implements  java.io.Serializable {
    private Long goodsId;
    private String goodsName;
    private String goodsPrice;

    @Override
    public String toString() {
        return "GoodsBean{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsBean goodsBean = (GoodsBean) o;
        return Objects.equals(goodsId, goodsBean.goodsId) &&
                Objects.equals(goodsName, goodsBean.goodsName) &&
                Objects.equals(goodsPrice, goodsBean.goodsPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsId, goodsName, goodsPrice);
    }

    public Long getGoodsId() {

        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public GoodsBean() {

    }

    public GoodsBean(Long goodsId, String goodsName, String goodsPrice) {

        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
    }
}
