package com.aop.log4j.constrant;

import org.springframework.context.annotation.Configuration;

@Configuration
public class SelfConstrants {
    /**
     * 存储在session中的用户名，即：登录的用户名
     */
    public static final String SESSION_USERNAME="userName";//存储在session中的用户名，即：登录的用户名


    /**
     * 当前操作的用户，当前登录的用户
     */
    public static final String CURRENT_USER="当前操作用户";
    /**
     * 日志记录的时间yyyy-MM-dd hh:MM:ss
     */
    public static final String LOG_TIME="日志记录时间";
    /**
     * 被记录日志的类-目标类
     */
    public static final String TARGET_CLASS="目标类";
    /**
     * 被记录日志的方法-目标方法
     */
    public static final String TARGET_METHOD="目标方法";
    /**
     * 请求参数
     */
    public static final String REQUEST_PARAMETER="请求参数";

    /**
     * 请求路径
     */
    public static final String REQUEST_URL="请求路径";

}
