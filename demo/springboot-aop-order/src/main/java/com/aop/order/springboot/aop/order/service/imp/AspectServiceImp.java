package com.aop.order.springboot.aop.order.service.imp;

import com.aop.order.springboot.aop.order.service.AspectService;
import org.springframework.stereotype.Service;

@Service
public class AspectServiceImp implements AspectService {
    @Override
    public String showMsg(String userName){
        System.out.println("******************"+"Helle,"+userName+",Welcome!");
        return "Helle,"+userName+",Welcome!";
    }

    @Override
   public  String greeting(String userName) {
        System.out.println("*****************This is a greettiong for "+userName);
        return "This is a greettiong for "+userName;
    }

    @Override
    public String setMsg(String user, String msg) {
        return setValue(user,msg);
    }


    private String setValue(String user,String msg){
        System.out.println("=================="+user+"+++++++++++++++"+msg);
        return "Hello,"+user+","+msg+"!";
    }


}
