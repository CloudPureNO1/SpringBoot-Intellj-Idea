package com.aop.order.springboot.aop.order.aspeict;

import net.sf.json.JSONArray;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Component
@Aspect
@Order(3)
public class WeblogOrder3 {
    @Pointcut(value="execution(public * com.aop.order.springboot.aop.order.service..*.*(..))")
    public void weblog3(){};

    @Before(value="weblog3()")
    public void before(JoinPoint joinPoint){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~order3 前置通知开始~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=servletRequestAttributes.getRequest();
        System.out.println("*******order3******remoteHost*****"+request.getRemoteHost());
        Object[] objs=joinPoint.getArgs();
        String json= JSONArray.fromObject(objs).toString();
        System.out.println("*******order3*****joinPoint 请求参数：json="+json);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order3 前置通知结束~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());

    }

    @After(value="weblog3()")
    public void after(JoinPoint joinPoint){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order3 后置通知开始~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
        System.out.println("*************************order3 后置通知"+joinPoint.getTarget());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order3 后置通知结束~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "obj",pointcut = "weblog3()")
    public void afterReturn(JoinPoint joinPoint,Object obj){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order3 后置还回通知开始~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
        System.out.println("*************************order3 后置通知还回参数为："+obj);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order3 后置还回通知结束~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());

    }

    @AfterThrowing(throwing = "throwable",pointcut = "weblog3()")
    public void afterThrow(JoinPoint joinPoint,Throwable throwable){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order3 后置还回通异常始~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
        if(throwable instanceof  NullPointerException ){
             System.out.println("*************************order3 后置空指针异常："+throwable.getMessage());
        }else if(throwable instanceof  ArrayIndexOutOfBoundsException){
             System.out.println("*************************order3 后置下标越界异常："+throwable.getMessage());
        }else{
            System.out.println("*************************order3 后置异常："+throwable.getMessage());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order3 后置还回异常结束~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());

    }

    @Around("weblog3()")
    public Object aruound(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order3 环绕通知开始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Object obj=null;
        try {
            System.out.println("***********************order3 环绕通知，继续往下执行（proceedingJoinPoint.proceed();）之前*******");
            obj=proceedingJoinPoint.proceed();
            System.out.println("***********************order3 环绕通知 结果："+obj);
        } catch (Throwable throwable) {
            System.out.println("***********************order3 环绕通知 异常："+throwable.getMessage());
            throwable.printStackTrace();
        }finally {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order3 环绕通知结束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        return obj;
    }
}
