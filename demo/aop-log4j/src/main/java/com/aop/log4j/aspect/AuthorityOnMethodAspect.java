package com.aop.log4j.aspect;

import com.aop.log4j.annotation.AuthorityOnMethodAnnotation;
import com.aop.log4j.aspect.util.AuthorityUtil;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
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


@Component
@Aspect
@Order(2)
public class AuthorityOnMethodAspect {
    private static final Logger logger= Logger.getLogger(AuthorityOnMethodAspect.class);
    @Pointcut("@annotation(com.aop.log4j.annotation.AuthorityOnMethodAnnotation)")
    public void authrityOnMethod(){}

    @Around("@annotation(autorityOnMehtod)")
    public Object around(ProceedingJoinPoint proceedingJoinPoint, AuthorityOnMethodAnnotation autorityOnMehtod) throws IOException {
        logger.info(">>>>>>>>>>>>>>>>>访问"+proceedingJoinPoint.getSignature().getName()+"方法的权限;组-"+autorityOnMehtod.authorityGroup()+"，单个权限-"+autorityOnMehtod.authoritySigle()+"，标志-"+autorityOnMehtod.authorityFlag()+"校验开始>>>>>>>>>>");
        Object obj=null;
        String resultCode="";
        String resultMsg="";
        ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request=servletRequestAttributes.getRequest();
        HttpServletResponse response=servletRequestAttributes.getResponse();
        HttpSession session=request.getSession();
        /**
         * 正式
         */
        //String userName= (String) session.getAttribute("userName");//取得登录的用户名
        String userName="test";//测试
        String authorityGroup=autorityOnMehtod.authorityGroup();
        String authoritySigle=autorityOnMehtod.authoritySigle();
        String authorityFlag=autorityOnMehtod.authorityFlag();
        //如果用户名不存在，提示信息
        if(StringUtils.isBlank(userName)){
            logger.error(">>>>>>>>>>>>>>>>>>>>>>没有登录或登录失效，请登录！>>>>>>>>>>>>>>>>");
            resultCode="NotLogged";
            resultMsg="没有登录或登录失效，请登录！";
        }else{
            /**
             * 正常登录，判断当前登录的用户是否拥有访问目标方法份权限 分两类情况
             * 1、方法上的是组权限 authorityGroup
             * 2、方法上的是单个权限 authoritySingle
             * 3、同时设置了权限，标志位and,则同时满足，其他为满足一个
             */
            if(AuthorityUtil.isPermissioned(userName,authorityGroup,authoritySigle,authorityFlag)){
                //拥有权限，继续往下执行目标方法
                try {
                   obj= proceedingJoinPoint.proceed();
                } catch (Throwable throwable) {
                    logger.error(">>>环绕"+proceedingJoinPoint.getSignature().getName()+"方法，继续执行时发生异常："+throwable.getMessage()+">>>.",throwable);
                }
            }else{
                //权限不知，提示
                logger.error(">>>>>>>>>>>>>>>."+userName+"没有访问"+proceedingJoinPoint.getSignature().getName()+"方法的权限>>>>>>>>>>>>>>>>>>");
                resultCode="PermissionDenied";
                resultMsg=userName+"没有访问"+proceedingJoinPoint.getSignature().getName()+"方法的权限";
            }
        }

        if(StringUtils.isNotBlank(resultCode)&&StringUtils.isNotBlank(resultMsg)){
            JSONObject jsonObject=new JSONObject();
            jsonObject.put("resultCode",resultCode);
            jsonObject.put("resultMsg",resultMsg);
            response.reset();
            response.setCharacterEncoding("utf-8");
            response.setContentType("text/palin;charset=utf-8");
            OutputStream outputStream=response.getOutputStream();
            outputStream.write(jsonObject.toString().getBytes("utf-8"));
        }

        return obj;
    }
}
