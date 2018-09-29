package com.unit.test.unittest.bean;


import java.util.Objects;


public class MyMsgBean {
    private Long msgId;
    private String msgName;
    private String msgDetail;

    public MyMsgBean() {
    }

    public MyMsgBean(Long msgId, String msgName, String msgDetail) {
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
    public String toString() {
        return "MyMsgBean{" +
                "msgId=" + msgId +
                ", msgName='" + msgName + '\'' +
                ", msgDetail='" + msgDetail + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyMsgBean myMsgBean = (MyMsgBean) o;
        return Objects.equals(msgId, myMsgBean.msgId) &&
                Objects.equals(msgName, myMsgBean.msgName) &&
                Objects.equals(msgDetail, myMsgBean.msgDetail);
    }

    @Override
    public int hashCode() {

        return Objects.hash(msgId, msgName, msgDetail);
    }
}
