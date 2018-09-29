package com.aop.log4j.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Order(2)
public @interface AuthorityOnMethodAnnotation {
    /**
     * 为方法单独设置的权限
     * authorityGroup  一组，默认为：普通用户组  ，因为一般用组对应权限
     * authoritySigle 单个权限：insert，update，添加user 等，默认为：所有权限，也可以把单个权限授予用户
     * ahthorityFlag and 两个权限同时设置时，必须同时满足;or 满足其中一个
     * @return
     */
    String authorityGroup() default "user";//一组权限，默认为：普通用户的权限，需要user用户组的权限，admin
    String authoritySigle() default "all";//单个权限：insert，update，等，默认为：所有权限
    String authorityFlag() default "or";//and 两个权限同时设置时，必须同时满足;or 满足其中一个
}
