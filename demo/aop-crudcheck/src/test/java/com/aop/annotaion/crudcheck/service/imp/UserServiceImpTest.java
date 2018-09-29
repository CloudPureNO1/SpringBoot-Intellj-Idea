package com.aop.annotaion.crudcheck.service.imp;

import com.aop.annotaion.crudcheck.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * 此处测试没有采用登录请求测试，（around 环绕通知方法中用session取到的用户为空，此处写死在session中测试）
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImpTest {

    @Autowired
    private UserService userService;
    @Test
    public void insertUser() {
        userService.insertUser(null);
    }

    @Test
    public void updateUser() {
        userService.updateUser(null);
    }

    @Test
    public void deleteUser() {
        userService.deleteUser(null);
    }

    @Test
    public void getUsers() {
        userService.getUsers();
    }

    @Test
    public void execAll() {
        userService.execAll();
    }
}