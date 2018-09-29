package com.aop.annotaion.crudcheck.aspect;

import com.aop.annotaion.crudcheck.annotation.UserAnnotaion;
import com.aop.annotaion.crudcheck.util.UserDataUtil;
import net.sf.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
@Order(2)
public class UserAspect {

    @Pointcut("@annotation(com.aop.annotaion.crudcheck.annotation.UserAnnotaion)")
    public void userAspect(){}

    @Around("@annotation(userAnnotaion)")
    public Object aroud(ProceedingJoinPoint proceedingJoinPoint,UserAnnotaion userAnnotaion) throws IOException {
        Object obj=null;
        Map<String,Object> map=null;
        String resultCode="";
        String resultMsg="";
        OutputStream outputStream=null;
        try {
            map=new HashMap<String,Object>();
            ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request=servletRequestAttributes.getRequest();
            HttpSession session=request.getSession();
            HttpServletResponse response=servletRequestAttributes.getResponse();
            outputStream=response.getOutputStream();
            //登录的用户名，从session中取得
            String userName= (String) session.getAttribute("userName");//取得登录的userName,session中取得
            //单元测试service时用
            //String userName="Pluto";
            //判断权限
            String authority=userAnnotaion.authority();//目标方法注解中的权限

            //判断登录的用户是否拥有权限，采用假数据
            if(userName==null||"".equals(userName.trim())||"null".equals(userName.toLowerCase().trim())){
                System.out.println(">>>>>>>>>没有登录，请先登录>>>>>>>>");
                resultCode="NotLogged";
                resultMsg="没有登录";
            }else if(UserDataUtil.isHasAuhority(userName,authority)){
                 System.out.println(">>>>>>>>>"+userName+">>>>>有权限执行/访问>>>>>>>>"+authority+">>>>>>>>");
                 obj=proceedingJoinPoint.proceed();
                 resultCode="HasAuthority";
                 resultMsg="有权限执行/访问";
            }else{
                System.out.println(">>>>>>>>>"+userName+">>>>>没有权限执行/访问>>>>>>>>"+authority+">>>>>>>>");
                resultCode="HasNotAuthority";
                resultMsg="没有权限执行/访问";
            }
            map.put("resultCode",resultCode);
            map.put("resultMsg",resultMsg);
            String json=JSONObject.fromObject(map).toString();
            response.setCharacterEncoding("UTF-8");
            response.setContentType("text/plain;charset=UTF-8");
            outputStream.write(json.getBytes("utf-8"));
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }

        return obj;
    }

}
