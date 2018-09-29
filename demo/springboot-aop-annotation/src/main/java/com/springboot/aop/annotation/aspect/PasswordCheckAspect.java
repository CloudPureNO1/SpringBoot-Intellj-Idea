package com.springboot.aop.annotation.aspect;

import com.springboot.aop.annotation.aspect.annotation.PasswordCheckAnnotation;
import com.sun.xml.internal.ws.client.ResponseContextReceiver;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Aspect
@Order(1)
public class PasswordCheckAspect {
    @Pointcut("@annotation(com.springboot.aop.annotation.aspect.annotation.PasswordCheckAnnotation)")
    public void checkPwd(){}

    @Before("@annotation(pwdCheck)")
    public void before(PasswordCheckAnnotation pwdCheck){
        if (pwdCheck.checkPwd()){
            //不通过，跳转
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>校验不通过，跳转");
            ServletRequestAttributes requestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request= requestAttributes.getRequest();
        }
    }
}
