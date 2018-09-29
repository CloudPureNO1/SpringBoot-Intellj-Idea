package com.springboot.aop.annotation.service.imp;

import com.springboot.aop.annotation.aspect.annotation.RunTimeAnnotation;
import com.springboot.aop.annotation.service.AllClassRunTimeService;
import org.springframework.stereotype.Service;

@Service
@RunTimeAnnotation(runTime = 20L)  //只在类上面用注解，方法上不用，无效，估计是注解的写法问题
public class AllClassRunTimeServiceImp implements AllClassRunTimeService {
    @Override

    public boolean login(String userName, String pwd) {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("***********login***********"+userName+":"+pwd);
        return false;
    }

    @Override

    public boolean regist(String userName, String pwd) {
        System.out.println("***********regist***********"+userName+":"+pwd);
        return false;
    }
}
