package com.aop.annotaion.crudcheck.bean;

import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserBean implements java.io.Serializable{
    private String userName;
    private String authority;

    @Override
    public String toString() {
        return "UserBean{" +
                "userName='" + userName + '\'' +
                ", authority='" + authority + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return Objects.equals(userName, userBean.userName) &&
                Objects.equals(authority, userBean.authority);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userName, authority);
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public UserBean() {

    }

    public UserBean(String userName, String authority) {
        this.userName = userName;

        this.authority = authority;
    }
}
