package com.aop.login.aspect;

import com.aop.login.annotation.LogonCheckAnnotation;
import com.aop.login.util.UserBeanTestUtil;
import net.sf.json.JSONObject;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class LogonCheckAspect {
    @Pointcut("@annotation(com.aop.login.annotation.LogonCheckAnnotation)")
    public void logonCheck(){}

    @Around("@annotation(logonCheckAnnotation)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, LogonCheckAnnotation logonCheckAnnotation){
        System.out.println(">>>>>>>>>>>>>>>>aop 校验是否登录开始>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        Object obj=null;
        Map<String,Object> map=null;
        String resultCode="";
        String resultMsg="";
        try {
            ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request=servletRequestAttributes.getRequest();
            HttpServletResponse response=servletRequestAttributes.getResponse();
            HttpSession session=request.getSession();
            session.setAttribute("logonName","marsMan");//service 单元测试用
            String logonName= (String) session.getAttribute("logonName");
            if(logonName==null||"".equals(logonName.trim())||"null".equalsIgnoreCase(logonName)){
                System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>没有登录>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                resultCode="NotLoggedIn";
                resultMsg="Current user is not logged in!";
            }else{
                if(!logonCheckAnnotation.userName().equals(logonName)){
                    System.out.println(">>>>>>>>>>>>>>>>当前登录的用户与注解中的用户不一致>>>>>>>>>>>>>>>>>>>>>>>>>>>");
                    resultCode="DifferentUsers";
                    resultMsg="当前登录的用户与注解中的用户不一致";
                }else{//校验用户是否有效，防止登陆后，用户失效了还能用的问题
                    //根据用户名得到，有效状态，此处写死，不从数据库里查询
                    if(!UserBeanTestUtil.isOk(logonName)){
                        System.out.println(">>>>>>>>>>>>>>>>>>>当前登录的用户已经失效！>>>>>>>>>>>>>>>>>>>>");
                        resultCode="UserInvalid";
                        resultMsg="当前登录的用户已经失效！";
                    }else{
                        System.out.println(">>>>>>>>>>>>>>>>>>>正常登录！>>>>>>>>>>>>>>>>>>>>");
                        resultCode="OK";
                        resultMsg="正常登录";
                    }
                }
            }
            if(resultCode.equals("Ok")){
                obj=proceedingJoinPoint.proceed();//继续执行注解的目标方法
            }else{
                response.reset();
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/plain;charset=UTF-8");
                map=new HashMap<String,Object>();
                map.put("resultCode","NotLoggedIn");
                map.put("resultMsg","Current user is not logged in!");
                String json=JSONObject.fromObject(map).toString();
                OutputStream outputStream=response.getOutputStream();
                System.out.println("+++++++++++++++++++"+json.getBytes("UTF-8"));
  /*               byte [] bytes=json.getBytes("UTF-8");
               for(byte b:bytes){
                    System.out.println(b);
                }*/
                outputStream.write(json.getBytes("UTF-8"));
              //  outputStream.flush();

            }
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }finally {
            System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>aop 校验是否登录结束>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        }
        return obj;
    }

}
