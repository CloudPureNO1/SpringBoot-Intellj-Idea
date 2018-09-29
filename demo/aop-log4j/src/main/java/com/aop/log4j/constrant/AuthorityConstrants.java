package com.aop.log4j.constrant;

import org.springframework.stereotype.Component;

/**
 * 如果是一组权限（即要查询数据库才能得到的一组权限），那么采用AUTHORITY开头加权限名
 * 一组权限名为：admin  则code为：AUTHORITY_ADMIN
 */
@Component
public class AuthorityConstrants {
    /**
     * 权限字符串 以Authority开头标志
     */
    public static final String AUTHORITY_STARTWITH="AUTHORITY";

    /**
     * 某类（比如，所有的insert 、delete、update、select、或者所有的curd
     */
    public static final String SYSTEM_STARTWITH="SYSTEM";

    /**
     * 所有权限  需要查询权限表得到
     */
    public static final String AUTHORITY_ALL="all";

    /**
     * 管理员权限  需要查询权限表得到
     */
    public static final String AUTHORITY_ADMIN="admin";
    /**
     * 普通用户权限  需要查询权限表得到
     */
    public static final String AUTHORITY_USER="user";

    /**
     * 对用户表拥有增、删、改、查的权限
     */
    public static final String AUTHORITY_USER_CRUD="userCurd";

    /**
     * 所有表的查询权限 以SYSTEM 开头
     */
    public static final String SYSTEM_SELECT="select";
}
