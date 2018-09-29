package com.aop.login.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogonCheckAnnotation {
    String userName() default "";
}
