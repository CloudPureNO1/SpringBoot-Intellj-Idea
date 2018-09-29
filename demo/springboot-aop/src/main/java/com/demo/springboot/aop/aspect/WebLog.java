package com.demo.springboot.aop.aspect;


import net.sf.json.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Component
public class WebLog {

    /**
     * execution(public * com.demo.springboot.aop.service..*.*(..))
     * public：配置公共方法
     * 第一个*：方法的还回类型
     * 第一个..:当前包及其所有子包
     * 第二个*：类
     * 第三个*:方法
     * 第二个..:所有参数类型
     * <p>
     * 即：匹配 com.demo.springboot.aop.service包和她子包下所有类中的所有公共方法（public修饰的方法）
     */
    @Pointcut("execution(public * com.demo.springboot.aop.service..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBeforeAdvice(JoinPoint joinPoint) throws Throwable {
        System.out.println(">>>>>>>>>>>>前置通知。。。。。。。。。。。。。");
        Object[] args = joinPoint.getArgs();//获取目标方法的 参数
        joinPoint.getThis();//aop代理类的信息
        joinPoint.getTarget();//被代理目标对象
        Signature signature = joinPoint.getSignature();//用到最多，被代理的签名
        String methodName = signature.getName();//代理的方法名
        String className = signature.getDeclaringTypeName();//代理类名
        Class clasz = signature.getDeclaringType();//代理类的Class对象

        //获取 HttpServletRequest 对象
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//获取请求对象
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        // 记录下请求内容
        System.out.println("URL : " + request.getRequestURL().toString());
        System.out.println("HTTP_METHOD : " + request.getMethod());
        System.out.println("IP : " + request.getRemoteAddr());
        System.out.println("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        System.out.println("ARGS : " + Arrays.toString(joinPoint.getArgs()));

        //aop 被代理对象方法的参数
/*        Enumeration<String> paramsEnum= request.getParameterNames();
        int i=0;
        while(paramsEnum.hasMoreElements()){
            i++;
            String key=paramsEnum.nextElement();
            String value=request.getParameter(key);
            System.out.println(">>>>>>>>>>>>>>>>请求参数"+i+":"+key+"="+value);
        }*/

        Map<String, Object> map = new HashMap<String, Object>();
        Enumeration<String> paramsEnum = request.getParameterNames();
        while (paramsEnum.hasMoreElements()) {
            map.put(paramsEnum.nextElement(), request.getParameter(paramsEnum.nextElement()));
        }
        if (map.size() > 0) {
            String jsonParam = JSONObject.fromObject(map).toString();
            System.out.println("请求的参数：" + jsonParam);
        } else {
            System.out.println("请求的参数为空");
        }
    }

    /**
     * webLog() 上面的切入点是 service
     *
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "webLog()")
    public void doAfterRetrunning(Object obj) {
        System.out.println(">>>>>>>>>>>>>>>处理完请求，返回信息：" + obj);
    }

    /**
     * 此处的切入点也是service，也可以直接应用 webLog()
     *
     * @param joinPoint
     * @param obj
     */
    @AfterReturning(returning = "obj", pointcut = "execution(public * com.demo.springboot.aop.service..*.*(..))")
    public void doAfterReturnning(JoinPoint joinPoint, Object obj) {
        System.out.println(">>>>>>>>>>>>>>>第二个后置请求还回" + obj);
    }



    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing 限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *      对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = "webLog()",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
        //目标方法名：
        System.out.println(joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
            System.out.println("发生了空指针异常!!!!!");
        }
    }


    /**
     * 后置最终通知（目标方法只要执行完了就会执行后置通知方法）
     *
     * @param joinPoint
     */
    @After("webLog()")
    public void doAfterAdvice(JoinPoint joinPoint) {
        System.out.println("后置通知执行了!!!!");
    }

    /**
     * 环绕通知：
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around("webLog()")
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
        System.out.println("环绕通知的目标方法名："+proceedingJoinPoint.getSignature().getName());
        try {//obj之前可以写目标方法执行前的逻辑
            Object obj = proceedingJoinPoint.proceed();//调用执行目标方法
            return obj;
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return null;
    }

}
