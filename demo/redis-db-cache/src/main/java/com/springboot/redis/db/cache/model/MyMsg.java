package com.springboot.redis.db.cache.model;

import java.util.Objects;

public class MyMsg implements  java.io.Serializable{
    private Long msgId;
    private String msgName;
    private String msgDetail;

    public MyMsg() {
    }

    public MyMsg(Long msgId, String msgName, String msgDetail) {
        this.msgId = msgId;
        this.msgName = msgName;
        this.msgDetail = msgDetail;
    }

    public Long getMsgId() {
        return msgId;
    }

    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }

    public String getMsgName() {
        return msgName;
    }

    public void setMsgName(String msgName) {
        this.msgName = msgName;
    }

    public String getMsgDetail() {
        return msgDetail;
    }

    public void setMsgDetail(String msgDetail) {
        this.msgDetail = msgDetail;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMsg myMsg = (MyMsg) o;
        return Objects.equals(msgId, myMsg.msgId) &&
                Objects.equals(msgName, myMsg.msgName) &&
                Objects.equals(msgDetail, myMsg.msgDetail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(msgId, msgName, msgDetail);
    }

    @Override
    public String toString() {
        return "MyMsg{" +
                "msgId=" + msgId +
                ", msgName='" + msgName + '\'' +
                ", msgDetail='" + msgDetail + '\'' +
                '}';
    }
}
