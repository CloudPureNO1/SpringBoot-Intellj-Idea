package com.aop.log4j.user.service;

import com.aop.log4j.user.bean.UserBean;

import java.util.Map;

public interface UserService {
    UserBean getUserById(Long userId);
    Boolean  addUser(UserBean user);
    void showMsg(UserBean user,String msg,Map<String,Object> map,String [] strings,int[]ints);
}
