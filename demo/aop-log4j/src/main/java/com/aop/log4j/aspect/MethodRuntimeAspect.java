package com.aop.log4j.aspect;

import com.aop.log4j.annotation.MethodRuntimeAnnotation;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(1)//int 值的最小范围，即最高优先级
public class MethodRuntimeAspect {
    private static final Logger logger=Logger.getLogger(MethodRuntimeAnnotation.class);

    @Pointcut("@annotation(com.aop.log4j.annotation.MethodRuntimeAnnotation)")
    public void methodRuntime(){}

    @Around("@annotation(mtdRuntime)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, MethodRuntimeAnnotation mtdRuntime){
        logger.info(">>>>>>>>>>>>>>>>>>>>>>>>方法运行时间aop环绕调用开始>>>>>>>>>>>>>>>>>>>>");
        long timeStart=System.currentTimeMillis();
        Object obj=null;
        try {
            obj=proceedingJoinPoint.proceed();
        } catch (Throwable throwable) {
            logger.error(proceedingJoinPoint.getSignature().getName()+"环绕调用异常"+throwable.getMessage(),throwable);
        }
        logger.info(">>>>>>>> >方法运行时间aop环绕调用结束>>>>"+proceedingJoinPoint.getSignature().getName()+"方法运行时间："+(System.currentTimeMillis()-timeStart)+"毫秒");
        return obj;
    }
}
