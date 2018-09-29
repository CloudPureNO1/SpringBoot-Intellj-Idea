package com.demo.springboot.aop.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class AspectAop {


    @Pointcut("execution(protected * com.demo.springboot.aop.service..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void before(JoinPoint joinPoint){
        System.out.println(">>>>>>>>>aop调用开始before>>>>>>>>>>>>>>");
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request=servletRequestAttributes.getRequest();
        System.out.println("request URL>>>>>>>>>>>>>>>>>>>>"+request.getRequestURI());
        System.out.println("HTTP_METHOD>>>>>>>>>>"+request.getMethod());
        System.out.println("IP>>>>>>>>>>>>>>>>>>"+request.getRemoteAddr());
        System.out.println("CLASS_METHOD >>>>>>>>>>"+joinPoint.getSignature().getDeclaringTypeName()+"."+joinPoint.getSignature().getName());
        System.out.println("ServerName>>>>>>>>>>>>>"+request.getServerName());
        System.out.println("args>>>>>>>>>>>>>>"+joinPoint.getArgs());
    }

    @After("webLog()")
    public void after(JoinPoint joinPoint){
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>aop方法最后执行after>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
    }

    @AfterThrowing(throwing = "ex",pointcut = "webLog()")
    public void throwLog(JoinPoint joinPoint,Exception ex){
        System.out.println(">>>>>>>>>>>>>>>>>>抛出异常日志>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+ex.getMessage());
    }


    @AfterReturning(returning = "returnObject",pointcut = "webLog()")
    public void afterReturn(Object returnObject){
        System.out.println(">>>>>>>>>>>>>>>>方法的返回值："+returnObject);
    }


    @Around("webLog()")
    public Object arround(ProceedingJoinPoint proceedingJoinPoint){
        Object obj=null;
        try {
            obj =proceedingJoinPoint.proceed();
            System.out.println(">>>>>>>>>>>>>>>>>>>>方法环绕proceed，结果是 :" +obj);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }
}
