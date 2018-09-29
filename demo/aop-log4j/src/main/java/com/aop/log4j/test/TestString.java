package com.aop.log4j.test;

import com.aop.log4j.user.bean.UserBean;
import net.sf.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TestString {
    public static void main(String []args){
        String []strings=new String []{"123","adfasd"};
        int [] ints=new int[]{12,34,56};
        showArrayString(strings);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("earth","earth");
        map.put("pluto","pluto");
        JSONObject json=new JSONObject();
        json.put("map",map);
        json.put("user",new UserBean(1L,"test","test123"));
        json.put("msg","SuperMan");
        json.put("strings",new String[]{"123","wangsm"});
        json.put("ints",new int []{123,231,345,45});
        System.out.println(json.toString());
    }

    public static void showArrayString(Object[] objects) {
        System.out.println(">>>>>>>>>>"+objects.getClass().toString());
        if(objects.getClass().toString().startsWith("class [")){
            System.out.println("*************"+Arrays.toString(objects));
            for(Object s:objects){
                System.out.println(s.getClass());
            }
        }else{
            System.out.println("*************"+objects.toString());
        }

        int i=Integer.MAX_VALUE;
        int i2=Integer.MIN_VALUE;
        System.out.println(i+"\n"+i2);
    }
}
