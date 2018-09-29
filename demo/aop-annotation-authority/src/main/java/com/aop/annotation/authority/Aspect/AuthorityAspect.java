package com.aop.annotation.authority.Aspect;

import com.aop.annotation.authority.AuthorityUtil;
import com.aop.annotation.authority.annotation.AuthorityAnnotation;
import com.aop.annotation.authority.bean.AuthorityBean;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


@Component
@Aspect
public class AuthorityAspect {
    @Autowired
    private AuthorityUtil authorityUtil;

    @Pointcut("@annotation(com.aop.annotation.authority.annotation.AuthorityAnnotation)")
    public void authorityPointCut(){}

    @Around("@annotation(authority)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, AuthorityAnnotation authority){
        boolean bPass=false;
        Object obj=null;
        String methodName= proceedingJoinPoint.getSignature().getName();
        Class<?>targetClass= proceedingJoinPoint.getTarget().getClass();
        Method method= null;
        try {
            method = targetClass.getMethod(methodName);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
         System.out.println(">>>>>>>>>>"+method.getReturnType()+">>>>>>"+method.getGenericReturnType()+">>>>>>>>>>"+method.getReturnType().getName());

        try {
            String value=authority.authorityValue();//取得目标方法的权限
            List<AuthorityBean>authorityList=authorityUtil.getAuthorityList();
            if(authorityList==null||authorityList.size()<1){
                System.out.println("********************权限对象中，没有配置权限，请先配置权限******************");

            }else{
                //没有配置目标的权限，则为所有权限
                if("all".equals(authority.authorityValue())){
                    System.out.println("**********************所有权限***************************************");
                    bPass=true;
                }else{
                    for(AuthorityBean bean:authorityList){
                        if(authority.authorityValue().equals(bean.getValue())){
                            System.out.println("**************拥有权限********************权限为："+authority.authorityValue());
                            bPass=true;
                            break;
                        }
                    }
                }
            }

            if(bPass){
                 obj=proceedingJoinPoint.proceed();
            }else{
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>权限不足，当前权限为："+authority.authorityValue());
            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return obj;
    }




}
