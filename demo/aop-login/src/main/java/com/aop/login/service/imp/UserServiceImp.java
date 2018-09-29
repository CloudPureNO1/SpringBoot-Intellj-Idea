package com.aop.login.service.imp;

import com.aop.login.annotation.LogonCheckAnnotation;
import com.aop.login.bean.UserBean;
import com.aop.login.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Override
    @LogonCheckAnnotation(userName = "human")
    public List<UserBean> getUsers() {
        return null;
    }

    @Override
    @LogonCheckAnnotation(userName = "marsMan")
    public UserBean getUser() {
        return null;
    }

    @Override
    @LogonCheckAnnotation(userName = "human")
    public Integer addUser(UserBean bean) {
        return null;
    }
}
