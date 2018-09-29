package com.aop.log4j.user.bean;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class UserBean  implements  java.io.Serializable{
    private Long userId;
    private String userName;
    private String userPwd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserBean userBean = (UserBean) o;
        return Objects.equals(userId, userBean.userId) &&
                Objects.equals(userName, userBean.userName) &&
                Objects.equals(userPwd, userBean.userPwd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, userPwd);
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public UserBean(Long userId, String userName, String userPwd) {

        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
    }

    public UserBean() {

    }
}
