package com.aop.order.springboot.aop.order.aspeict;

import net.sf.json.JSONArray;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


@Component
@Aspect
@Order(2)
public class WebLogOrder2 {
    @Pointcut(value="execution( * com.aop.order.springboot.aop.order.service..*.*(..))")
    public void webLog(){}

    @Before("webLog()")
    public void before(JoinPoint joinPoint){

/*       ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=servletRequestAttributes.getRequest();
        System.out.println(">>>>>>>>>>>>>请求地址："+request.getRequestURI());
        System.out.println(">>>>>>>>>>>>>>内容路径：>"+request.getContextPath());
        System.out.println(">>>>>>>>>>>>>>请求方法 ："+request.getMethod());
        System.out.println(">>>>>>>>>>>>>>servletPath:"+request.getServletPath());
        System.out.println(">>>>>>>>>>request 获取请求参数开始");
        Map<String,Object> map=new HashMap<String,Object>();
        Enumeration<String> enumeration= request.getParameterNames();
        if(enumeration.hasMoreElements()){
             map.put(enumeration.nextElement(),request.getParameter(enumeration.nextElement()));
        }
        String json=JSONObject.fromObject(map).toString();
        System.out.println(">>>>>>>>>>>>ruequest 获取参数请求json字符串："+json);*/
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~order2 前置通知开始~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=servletRequestAttributes.getRequest();
        System.out.println("*******order2******remoteHost*****"+request.getRemoteHost());
        Object[] objs=joinPoint.getArgs();
        String json= JSONArray.fromObject(objs).toString();
        System.out.println("*******order2*****joinPoint 请求参数：json="+json);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order2 前置通知结束~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());

    }

    @After("webLog()")
    public void after(JoinPoint joinPoint){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order2 后置通知开始~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
        System.out.println("*************************order2 后置通知"+joinPoint.getTarget());
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order2 后置通知结束~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
    }

    @AfterReturning(returning = "obj",pointcut = "webLog()")
    public void afterReturn(JoinPoint joinPoint,Object obj){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order2 后置还回通知开始~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
        System.out.println("*************************order2 后置通知还回参数为："+obj);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order2 后置还回通知结束~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());

    }

    @AfterThrowing(throwing = "throwable",pointcut = "webLog()")
    public void afterThrow(JoinPoint joinPoint,Throwable throwable){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order2 后置还回通异常始~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());
        if(throwable instanceof  NullPointerException ){
            System.out.println("*************************order2 后置空指针异常："+throwable.getMessage());
        }else if(throwable instanceof  ArrayIndexOutOfBoundsException){
            System.out.println("*************************order2后置下标越界异常："+throwable.getMessage());
        }else{
            System.out.println("*************************order2后置异常："+throwable.getMessage());
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order2后置还回异常结束~~~~~~~~~~~~~~~~~~~~~~~~"+joinPoint.getSignature().getName());

    }

    @Around("webLog()")
    public Object aruound(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order2 环绕通知开始~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Object obj=null;
        try {
            System.out.println("***********************order2 环绕通知，继续往下执行（proceedingJoinPoint.proceed();）之前*******");
            obj=proceedingJoinPoint.proceed();
            System.out.println("***********************order2 环绕通知 结果："+obj);
        } catch (Throwable throwable) {
            System.out.println("***********************order2 环绕通知 异常："+throwable.getMessage());
            throwable.printStackTrace();
        }finally {
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~order2 环绕通知结束~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        }
        return obj;
    }
}
