package com.mybatis.annotation.mybatis.annotation.model;

import java.util.Objects;

public class GoodsModel implements  java.io.Serializable {
    private Long goodsId;
    private String goodsName;
    private String goodsPrice;

    public GoodsModel() {
    }

    public GoodsModel(Long goodsId, String goodsName, String goodsPrice) {
        this.goodsId = goodsId;
        this.goodsName = goodsName;
        this.goodsPrice = goodsPrice;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsModel that = (GoodsModel) o;
        return Objects.equals(goodsId, that.goodsId) &&
                Objects.equals(goodsName, that.goodsName) &&
                Objects.equals(goodsPrice, that.goodsPrice);
    }

    @Override
    public int hashCode() {

        return Objects.hash(goodsId, goodsName, goodsPrice);
    }

    @Override
    public String toString() {
        return "GoodsModel{" +
                "goodsId=" + goodsId +
                ", goodsName='" + goodsName + '\'' +
                ", goodsPrice='" + goodsPrice + '\'' +
                '}';
    }
}
