package com.aop.login.bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserBean implements  java.io.Serializable{
    private String userName;
    private String pwd;
    private Boolean bFlag;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Boolean getbFlag() {
        return bFlag;
    }

    public void setbFlag(Boolean bFlag) {
        this.bFlag = bFlag;
    }

    public UserBean() {
    }

    public UserBean(String userName, String pwd, Boolean bFlag) {
        this.userName = userName;
        this.pwd = pwd;
        this.bFlag = bFlag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return Objects.equals(userName, userBean.userName) &&
                Objects.equals(pwd, userBean.pwd) &&
                Objects.equals(bFlag, userBean.bFlag);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, pwd, bFlag);
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", bFlag=" + bFlag +
                '}';
    }
}
