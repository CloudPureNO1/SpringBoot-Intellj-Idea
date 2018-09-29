package com.aop.log4j.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(1)
public @interface MethodRuntimeAnnotation {
    long runtimeValue() default 0L;
}
