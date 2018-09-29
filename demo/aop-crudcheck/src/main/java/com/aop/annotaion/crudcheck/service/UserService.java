package com.aop.annotaion.crudcheck.service;

import com.aop.annotaion.crudcheck.bean.UserBean;

import java.util.List;

public interface UserService {
    Integer  insertUser(UserBean userBean);
    Integer  updateUser(UserBean userBaen);
    Integer  deleteUser(UserBean userBean);
    List<UserBean> getUsers();
    void execAll();
}
