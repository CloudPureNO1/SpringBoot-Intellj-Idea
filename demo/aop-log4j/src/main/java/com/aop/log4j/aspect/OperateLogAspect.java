package com.aop.log4j.aspect;

import com.aop.log4j.constrant.SelfConstrants;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
@Aspect
@Order(-777)
public class OperateLogAspect {
    private static final  Logger logger=Logger.getLogger(OperateLogAspect.class);

    @Pointcut("execution(* com.aop.log4j..*.*(..))")
    public void operateLog(){}

    @Before("operateLog()")
    public void before(JoinPoint joinPoint){
            ServletRequestAttributes servletRequestAttributes= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request=servletRequestAttributes.getRequest();
            HttpSession session=request.getSession();
    /*        Object [] args=proceedingJoinPoint.getArgs();
            Arrays.toString(args)*/;
            String jsonArgs= JSONArray.fromObject(joinPoint.getArgs()).toString();

/*            String msg="当前操作用户："+session.getAttribute("userName")
                    +"\t操作时间："+(new SimpleDateFormat("yyyy-mm-dd hh:MM:ss").format(new Date()))
                    +"\t目标类："+proceedingJoinPoint.getTarget()
                    +"\t目标方法："+proceedingJoinPoint.getSignature().getName()
                    +"\t请求参数："+jsonArgs;*/
            JSONObject jsonObject=new JSONObject();
            jsonObject.put(SelfConstrants.CURRENT_USER,session.getAttribute(SelfConstrants.SESSION_USERNAME)==null?"":session.getAttribute(SelfConstrants.SESSION_USERNAME));
            jsonObject.put(SelfConstrants.LOG_TIME,(new SimpleDateFormat("yyyy-mm-dd hh:MM:ss").format(new Date())));
            jsonObject.put(SelfConstrants.TARGET_CLASS,joinPoint.getTarget().toString());
            jsonObject.put(SelfConstrants.TARGET_METHOD,joinPoint.getSignature().getName());
            jsonObject.put(SelfConstrants.REQUEST_PARAMETER,jsonArgs);
            jsonObject.put(SelfConstrants.REQUEST_URL,request.getRequestURI());
            logger.debug(jsonObject.toString());

    }




}
