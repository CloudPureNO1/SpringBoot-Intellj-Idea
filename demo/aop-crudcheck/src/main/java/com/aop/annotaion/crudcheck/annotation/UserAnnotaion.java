package com.aop.annotaion.crudcheck.annotation;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Pointcut;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface UserAnnotaion {
   String  authority() default "all";
}
