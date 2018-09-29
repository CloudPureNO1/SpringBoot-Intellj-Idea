package com.springboot.aop.annotation.aspect.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Order(1001)
public @interface PasswordCheckAnnotation {
    boolean checkPwd() default  false;
}
