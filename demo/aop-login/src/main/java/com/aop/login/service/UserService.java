package com.aop.login.service;

import com.aop.login.bean.UserBean;

import java.util.List;

public interface UserService {
    List<UserBean>  getUsers();
    UserBean getUser();
    Integer addUser(UserBean  bean);
}
