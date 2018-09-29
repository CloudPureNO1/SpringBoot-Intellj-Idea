package com.springboot.aop.annotation.aspect;

import com.springboot.aop.annotation.aspect.annotation.LogonCheckAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class LogonCheckAspect {
    @Pointcut("@annotation(com.springboot.aop.annotation.aspect.annotation.LogonCheckAnnotation)")
    public void logonCheck(){}

    @Around("@annotation(logonCheckAnnotation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, LogonCheckAnnotation logonCheckAnnotation){
        Object obj=null;
        try {
            if(logonCheckAnnotation.loginCheck()){//登录正确，继续执行
                 obj=proceedingJoinPoint.proceed();
            }else{
                 System.out.println(">>>>>>>>>>>请先登录！");
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}
