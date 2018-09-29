package com.aop.annotaion.crudcheck.util;

import com.aop.annotaion.crudcheck.bean.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDataUtil {

    public static boolean isHasAuhority(String userName,String authority){
        List<UserBean>list=new ArrayList<UserBean>();
        UserBean user=new UserBean();
        user.setUserName("Pluto");
        user.setAuthority("insert");
        list.add(user);

        user=new UserBean("Pluto","delete");
        list.add(user);

        user=new UserBean("Pluto","update");
        list.add(user);

        user=new UserBean("Pluto","select");
        list.add(user);

        if(list!=null&&list.size()>0){
            for(UserBean bean:list){
                if(userName.equals(bean.getUserName())&&authority.equals(bean.getAuthority()))
                    return true;
            }
        }
        return false;
    }
}
