package com.springboot.aop.annotation.aspect.annotation;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * 需要有对应的面向切面类
 */
@Documented  //文档，可以不要

@Retention(RetentionPolicy.RUNTIME)           //运行
@Target({ElementType.METHOD,ElementType.TYPE})   //目标的类型是方法和属性
@Order(Ordered.HIGHEST_PRECEDENCE)      //最高进程优先级  int HIGHEST_PRECEDENCE = -2147483648;
public @interface RunTimeAnnotation {
    long runTime() default 0L;  //传入的响应时间
}
