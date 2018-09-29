package com.mybatis.annotation.mybatis.annotation.model;

import java.util.Objects;

public class UserModel implements  java.io.Serializable{
    private Long userId;
    private String userName;
    private String userPwd;

    public UserModel() {


    }

    public UserModel(Long userId, String userName, String userPwd) {
        this.userId = userId;
        this.userName = userName;
        this.userPwd = userPwd;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserModel userModel = (UserModel) o;
        return Objects.equals(userId, userModel.userId) &&
                Objects.equals(userName, userModel.userName) &&
                Objects.equals(userPwd, userModel.userPwd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, userName, userPwd);
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userPwd='" + userPwd + '\'' +
                '}';
    }
}
