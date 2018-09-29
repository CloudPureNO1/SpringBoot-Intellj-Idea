package com.aop.login.util;

import com.aop.login.bean.UserBean;

import java.util.ArrayList;
import java.util.List;

public class UserBeanTestUtil {
    public static boolean isOk(String userName){
        List<UserBean> list=new ArrayList<UserBean>();
        UserBean bean=new UserBean();
        bean.setbFlag(false);
        bean.setUserName("human");
        bean.setPwd("human");
        list.add(bean);

        bean=new UserBean();
        bean.setbFlag(true);
        bean.setUserName("marsMan");
        bean.setPwd("marsMan");
        list.add(bean);

        boolean bFlag=false;
        if(list!=null&&list.size()>0){
            for(UserBean user:list){
                if(userName.equals(user.getUserName())){
                    bFlag=true;
                }
            }
        }

        return bFlag;
    }
}
