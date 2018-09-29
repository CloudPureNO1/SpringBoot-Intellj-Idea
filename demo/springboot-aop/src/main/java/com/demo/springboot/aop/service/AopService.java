package com.demo.springboot.aop.service;

import org.springframework.stereotype.Service;

@Service
public class AopService {
    public void showMsg(String userName, String passwd) {
        System.out.println("-------->showMsg:" + userName + ",passwd");
    }

    public void showException() {
        throw new NullPointerException();
    }

    public void showExceptionUnKown(){
        throw new NumberFormatException();
    }
    public void showException2(){
        throw new NullPointerException();
    }
}