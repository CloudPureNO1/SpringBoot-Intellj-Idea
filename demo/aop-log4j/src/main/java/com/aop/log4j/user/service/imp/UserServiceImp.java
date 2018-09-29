package com.aop.log4j.user.service.imp;

import com.aop.log4j.annotation.AuthorityOnMethodAnnotation;
import com.aop.log4j.annotation.MethodRuntimeAnnotation;
import com.aop.log4j.user.bean.UserBean;
import com.aop.log4j.user.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service
public class UserServiceImp implements UserService {
private static final Logger logger= Logger.getLogger(UserServiceImp.class);

    @Override
    public UserBean getUserById(Long userId) {
        logger.debug(">>>>>>>>>>>>>>DEBUG> 级别日志>>>>getUserById>>>>>>>>>>>>>>");
        logger.info(">>>>>>>>>>>>>>INFO> 级别日志>>>>getUserById>>>>>>>>>>>>>>");
        logger.warn(">>>>>>>>>>>>>>>WARN 级别日志>>>>getUserById>>>>>>>>>>>>>>");
        logger.error(">>>>>>>>>>>>>>ERROR> 级别日志>>>>getUserById>>>>>>>>>>>>>>");
        try{
            UserBean bean=null;
            bean.getUserName();
        }catch (NullPointerException ne){
            logger.error(">>>>>>>>>空指针异常："+ne.getMessage());
        }
        return null;
    }

    @Override
    @MethodRuntimeAnnotation(runtimeValue = 1)
    public Boolean addUser(UserBean user) {
        logger.debug(">>>>>>>>>>>>>>DEBUG> 级别日志>>>>addUser>>>>>>>>>>>>>>");
        logger.info(">>>>>>>>>>>>>>INFO> 级别日志>>>>addUser>>>>>>>>>>>>>>");
        logger.warn(">>>>>>>>>>>>>>>WARN 级别日志>>>>addUser>>>>>>>>>>>>>>");
        logger.error(">>>>>>>>>>>>>>ERROR> 级别日志>>>>addUser>>>>>>>>>>>>>>");

         try{
             String sNum="asf%";
             Long lNum=Long.valueOf(sNum);
         }catch(NumberFormatException ne){
             logger.error(">>>>>>>>>>>>>>>数字转换异常："+ne.getMessage(),ne);
         }
        return null;
    }

    @Override
    @MethodRuntimeAnnotation

    public void showMsg(UserBean user, String msg, Map<String, Object> map,String [] strings,int []ints) {
        logger.debug(">>>>>>>>>>>>>>DEBUG> 级别日志>>>>addUser>>>>>>>>>>>>>>");
        logger.info(">>>>>>>>>>>>>>INFO> 级别日志>>>>addUser>>>>>>>>>>>>>>");
        logger.warn(">>>>>>>>>>>>>>>WARN 级别日志>>>>addUser>>>>>>>>>>>>>>");
        logger.error(">>>>>>>>>>>>>>ERROR> 级别日志>>>>addUser>>>>>>>>>>>>>>");
    }
}
