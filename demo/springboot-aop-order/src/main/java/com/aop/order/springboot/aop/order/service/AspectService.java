package com.aop.order.springboot.aop.order.service;

public interface AspectService {

    public String showMsg(String userName);
    public String greeting(String userName);
    public String setMsg(String user,String msg);
}
