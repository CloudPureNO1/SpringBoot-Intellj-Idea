package com.mybatis.annotation2.mytatis.annotation2.mapper;

import com.mybatis.annotation2.mytatis.annotation2.model.UserModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;



    List<UserModel>list=null;
    @Before
    public void setUp(){
        list=new ArrayList<UserModel>();
        UserModel user=new UserModel();
        user.setUserName("wangsm");
        user.setUserPwd("wangsm777");
        list.add(user);

        user=new UserModel();
        user.setUserName("wangxy");
        user.setUserPwd("wangxy777");
        list.add(user);

        user=new UserModel();
        user.setUserName("fly_sky");
        user.setUserPwd("fly_sky_777");
        list.add(user);

    }

    @Test
    public void insert() {
        int userId=userMapper.insert(1L,"wangxy","wangxy666");
        assertEquals(1L,userId);
    }

    @Test
    public void insertByModel() {
        UserModel user=new UserModel();
        user.setUserName("wangsenming");
        user.setUserPwd("wangsenming777");
        userMapper.insertByModel(user);//此处执行插入后，可以获取插入的序列号
        Long userId=user.getUserId();
        assertEquals("",userId);//应为开始传入的user,userId为null，所以此处假装值要为“”，会报错,如下
        /**
         * java.lang.AssertionError:
         * Expected :
         * Actual   :10011
         *  <Click to see difference>
         */

    }

    @Test
    public void insertByMap() {
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("userName","FlyBirds");
        map.put("userPwd","FlayBirds");
        userMapper.insertByMap(map);
        Long userId=Long.valueOf(map.get("userId").toString());
        assertEquals(null,userId);

        /**
         * java.lang.AssertionError:
         * Expected :null
         * Actual   :10012     实际取得的值为：10012 序列生成
         *  <Click to see difference>
         */
    }

    @Test
    public void insertBatchByScript() {
        userMapper.insertBatchByScript(list);
        for(UserModel u:list){
            System.out.println(">userId开始没有值>>>>>>>>>>>>>>>>>>>>>"+u.getUserId());
        }
    }

    @Test
    public void providerInsert() {
        userMapper.providerInsert(list);
        for(UserModel user:list){
            System.out.println(">userId开始没有值>>>>>>>>>>>>>>>>>>>>>"+user.getUserId());
        }
    }
}