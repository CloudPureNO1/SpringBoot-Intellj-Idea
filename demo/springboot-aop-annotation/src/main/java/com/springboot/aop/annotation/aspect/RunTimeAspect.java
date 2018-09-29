package com.springboot.aop.annotation.aspect;

import com.springboot.aop.annotation.aspect.annotation.RunTimeAnnotation;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)
public class RunTimeAspect {

    @Pointcut("@annotation(com.springboot.aop.annotation.aspect.annotation.RunTimeAnnotation))")
    public void runTimeAspect(){}

    @Around("@annotation(runTimeAnnotation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, RunTimeAnnotation runTimeAnnotation){
        long timeStart=System.currentTimeMillis();
        System.out.println("*********************************方法开始执行时间："+timeStart);
        Object obj=null;
        try {
            obj=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            System.out.println("*****************************方法执行结束花费时间："+(System.currentTimeMillis()-timeStart)+"毫秒");
            if (System.currentTimeMillis()-timeStart>runTimeAnnotation.runTime()){
                System.out.println("**************************方法运行时间超出预期的时间："+runTimeAnnotation.runTime()+"毫秒");
            }
        }
        return obj;
    }
}

