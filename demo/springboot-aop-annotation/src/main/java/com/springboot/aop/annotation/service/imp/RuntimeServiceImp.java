package com.springboot.aop.annotation.service.imp;

import com.springboot.aop.annotation.aspect.annotation.RunTimeAnnotation;
import com.springboot.aop.annotation.service.RuntimeService;
import org.springframework.stereotype.Service;

@Service

public class RuntimeServiceImp implements RuntimeService {

    @RunTimeAnnotation(runTime = 20L)
    @Override
    public String showMsg(String msg) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("******************"+msg);
        return msg;
    }

    @Override
    public String showMsg2(String msg) {
        System.out.println("***********8没有自定义注解"+msg);
        return msg;
    }
}
