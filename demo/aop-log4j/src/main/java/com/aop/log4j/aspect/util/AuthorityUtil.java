package com.aop.log4j.aspect.util;


public class AuthorityUtil {
    public static Boolean isPermissioned(String userName,String authorityGroup,String authoritySingle,String authorityFlag){
        //查询数据校验
        boolean a=true;// 用户是否拥有authorityGroup的权限
        boolean b=true;//用户是否拥有authoritySingle的权限

        if("and".equals(authorityFlag)&&a&&b){
            return true;
        }else if("or".equals(authorityFlag)&&(a||b)){
            return true;
        }else{
            return false;
        }
    }
}
