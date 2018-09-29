package com.aop.annotation.authority.annotation;


import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface AuthorityAnnotation {
    String authorityValue() default  "all";//默认为所有权限
}
