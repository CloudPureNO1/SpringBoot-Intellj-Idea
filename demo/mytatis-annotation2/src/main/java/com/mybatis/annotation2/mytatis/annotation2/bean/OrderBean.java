package com.mybatis.annotation2.mytatis.annotation2.bean;

import java.util.Objects;

/**
 * 用于插入order,时的辅助bean
 */
public class OrderBean implements java.io.Serializable{
    private Long orderId;
    private Long userId;
    private Long goodsId;

    public OrderBean() {
    }

    public OrderBean(Long userId,Long goodsId){
        this.userId=userId;
        this.goodsId=goodsId;
    }
    public OrderBean(Long orderId, Long userId, Long goodsId) {
        this.orderId = orderId;
        this.userId = userId;
        this.goodsId = goodsId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderBean orderBean = (OrderBean) o;
        return Objects.equals(orderId, orderBean.orderId) &&
                Objects.equals(userId, orderBean.userId) &&
                Objects.equals(goodsId, orderBean.goodsId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(orderId, userId, goodsId);
    }

    @Override
    public String toString() {
        return "OrderBean{" +
                "orderId=" + orderId +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                '}';
    }
}
