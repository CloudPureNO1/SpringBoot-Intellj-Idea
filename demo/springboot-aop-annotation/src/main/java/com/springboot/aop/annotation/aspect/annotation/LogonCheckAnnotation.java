package com.springboot.aop.annotation.aspect.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Documented

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(-1000)
public @interface LogonCheckAnnotation {
    boolean loginCheck() default false;
}
