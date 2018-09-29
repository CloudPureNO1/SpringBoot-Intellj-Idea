package com.aop.annotaion.crudcheck.service.imp;

import com.aop.annotaion.crudcheck.annotation.UserAnnotaion;
import com.aop.annotaion.crudcheck.bean.UserBean;
import com.aop.annotaion.crudcheck.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Override
    @UserAnnotaion(authority = "insert")
    public Integer insertUser(UserBean userBean) {
        System.out.println("******************执行 insertUser********************");
        return 0;
    }

    @Override
    @UserAnnotaion(authority = "update")
    public Integer updateUser(UserBean userBaen) {
        System.out.println("******************执行 updateUser********************");
        return 0;
    }

    @Override
    @UserAnnotaion(authority = "delete")
    public Integer deleteUser(UserBean userBean) {
        System.out.println("******************执行 deleteUser********************");
        return 0;
    }

    @Override
    @UserAnnotaion(authority = "select")
    public List<UserBean> getUsers() {
        System.out.println("******************执行 getUsers********************");
        return null;
    }

    @Override
    @UserAnnotaion(authority = "execAll")
    public void execAll() {
        System.out.println("******************执行 execAll********************");
    }
}
