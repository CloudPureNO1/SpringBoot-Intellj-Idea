package com.mybatis.annotation.mybatis.annotation.mapper;

import com.mybatis.annotation.mybatis.annotation.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {


    @Autowired
    private UserMapper userMapper;


    @Test
    public void insert() {
        UserModel user=new UserModel(1L,"wangsm","wangsm777");
        assertEquals(1,userMapper.insert(user));
    }

    @Test
    public void insertBySequence() {
        //此处调用的是sequence插入id，所以，id 不是2
        UserModel user=new UserModel(2L,"CloudPure","CloudPure777");
        assertEquals(1,userMapper.insertBySequence(user));
        System.out.println(">>>>>>>>>>>>>>>>>>>>"+user.getUserId());//@SelectKey 可以输出最后插入的值，此处id是新插入的序列号，而不是2
    }

    @Test
    public void insertBySquParam() {
       assertEquals(1, userMapper.insertBySquParam("FaithShip","FaithShip"));
    }

    @Test
    public void insertByMap(){
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userName","Fly_Sky");
        map.put("userPwd","Fly_Sky");
        assertEquals(1,userMapper.insertByMap(map));
        System.out.println(">>>>>>>>>>>>>>"+map.get("userId"));//此处采用了SelectKey 注解，所以可以拿到，插入后的值

    }

    @Test
    public void getUserById() {
        assertEquals("wangsm1",userMapper.getUserById(10008L).getUserName());
    }

    @Test
    public void getAllUser() {
        List<UserModel>userList=userMapper.getAllUser();
        if(userList!=null&&userList.size()>0){
            for(UserModel user:userList){
                System.out.println(">>>>>>>>>>>"+user.toString());
            }
        }
    }
}