package com.aop.login.service.imp;

import com.aop.login.bean.UserBean;
import com.aop.login.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImpTest {

    @Autowired
    private UserService userService;
    @Test
    public void getUsers() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>getUsers 测试");
        userService.getUsers();
    }

    @Test
    public void getUser() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>getUser 测试");
        userService.getUser();
    }

    @Test
    public void addUser() {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>addUser 测试");
        userService.addUser(new UserBean("PlutoMan","Pluto Plante",true));
    }
}