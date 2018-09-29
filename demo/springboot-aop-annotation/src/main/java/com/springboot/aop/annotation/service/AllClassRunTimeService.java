package com.springboot.aop.annotation.service;

public interface AllClassRunTimeService {
    boolean login(String userName,String pwd);
    boolean regist(String userName,String pwd);
}
