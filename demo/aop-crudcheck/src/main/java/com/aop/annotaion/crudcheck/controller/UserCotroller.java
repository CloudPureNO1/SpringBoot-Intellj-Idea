package com.aop.annotaion.crudcheck.controller;

import com.aop.annotaion.crudcheck.service.UserService;
import org.apache.catalina.connector.CoyoteOutputStream;
import org.apache.catalina.connector.OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;

@Controller
@RequestMapping(value="/user")
public class UserCotroller {
    @Autowired
    private UserService userService;

    /**
     * 做单元测试用
     * @return
     */
    @RequestMapping(value="/testAll")
    @ResponseBody
    public String testAll(){
        userService.insertUser(null);
        userService.deleteUser(null);
        userService.updateUser(null);
        userService.getUsers();
        userService.execAll();
        return "";
    }

    /**
     * 真实hppt请求用，通过浏览器或Postman
     * @return
     */
    @RequestMapping(value="/test")
    @ResponseBody
    public String test(HttpServletResponse response) throws IOException {
        userService.insertUser(null);
        userService.deleteUser(null);
        userService.updateUser(null);
        userService.getUsers();
        userService.execAll();
        OutputStream outputStream=response.getOutputStream();
        System.out.println(outputStream.toString());
        CoyoteOutputStream coyoteOutputStream= (CoyoteOutputStream) outputStream;
        Class<CoyoteOutputStream>clazz1=CoyoteOutputStream.class;
        try {
            Field fieldOb=clazz1.getDeclaredField("ob");//通过反射取得ob定义属性，OutputBuffer
            if(fieldOb.getType().toString().endsWith("OutputBuffer")){
                fieldOb.setAccessible(true);//
                OutputBuffer outputBuffer= (OutputBuffer) fieldOb.get(coyoteOutputStream);
                Class<OutputBuffer>clazz2=OutputBuffer.class;
                Field fieldBb=clazz2.getDeclaredField("bb");
                if(fieldBb.getType().toString().endsWith("ByteBuffer")){
                    fieldBb.setAccessible(true);
                    ByteBuffer byteBuffer= (ByteBuffer) fieldBb.get(outputBuffer);
                    Class<ByteBuffer>clazz3=ByteBuffer.class;
                    Field fieldHb=clazz3.getDeclaredField("hb");
                    String ty=fieldHb.getType().toString();
                    if(fieldHb.getType().toString().endsWith("[B")){//byte[] 的反射类型为：class [B    byte 的为 byte   Byte[] 的为：class [Ljava.lang.Byte
                        fieldHb.setAccessible(true);
                        byte [] bytes= (byte[]) fieldHb.get(byteBuffer);
                        String str=new String(bytes,"UTF-8");
                        System.out.println("*********还回结果："+str);
                        response.resetBuffer();//如果不resetBuffer()，那么 return "success" 中的值无效
                    }
                }
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "success";
    }
}
