package com.mybatis.annotation.mybatis.annotation.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class OrderModel implements  java.io.Serializable {
    private Long orderId;
    private UserModel user=new UserModel();
    private Set<GoodsModel> goodsSet=new HashSet<GoodsModel>();

    public OrderModel() {
    }

    public OrderModel(UserModel user, Set<GoodsModel> goodsSet) {
        this.user = user;
        this.goodsSet = goodsSet;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public Set<GoodsModel> getGoodsSet() {
        return goodsSet;
    }

    public void setGoodsSet(Set<GoodsModel> goodsSet) {
        this.goodsSet = goodsSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderModel that = (OrderModel) o;
        return Objects.equals(orderId, that.orderId) &&
                Objects.equals(user, that.user) &&
                Objects.equals(goodsSet, that.goodsSet);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, user, goodsSet);
    }

    @Override
    public String toString() {
        return "OrderModel{" +
                "orderId=" + orderId +
                ", user=" + user +
                ", goodsSet=" + goodsSet +
                '}';
    }
}
