package com.aop.log4j.user.service.imp;

import com.aop.log4j.annotation.MethodRuntimeAnnotation;
import com.aop.log4j.user.bean.UserBean;
import com.aop.log4j.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.swing.*;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImpTest {
    @Autowired
    private UserService userService;

    @Test
    public void getUserById() {
        userService.getUserById(0L);
    }

    @Test
    public void addUser() {
        userService.addUser(new UserBean());
    }

    @Test

    public void showMsg(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("earth","earth");
        map.put("pluto","pluto");

        userService.showMsg(new UserBean(1L,"fly","fly123"),"SuperMan",map,new String[]{"123","wangsm"},new int []{123,231,345,45});
    }
}