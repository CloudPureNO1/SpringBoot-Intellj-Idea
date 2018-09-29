package com.aop.order.springboot.aop.order.aspeict;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Controller;

@Controller
@Aspect
@Order(1)
public class WeblogOrder0 {
    @Pointcut(value="execution(public * com.aop.order.springboot.aop.order.service..*.*(..))")
    public void weblog(){}

    @Around("weblog()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint){
        long time=System.currentTimeMillis();
        System.out.println("======================计时 aop 环绕通知开始===============================开始时间："+time);
        Object obj=null;
        try {
            obj=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            System.out.println("======================计时 aop 环绕通知结束>>>>>>>>>>>>>>本次方法执行耗时；"+(System.currentTimeMillis()-time)+"毫秒");
        }
        return obj;
    }

}
