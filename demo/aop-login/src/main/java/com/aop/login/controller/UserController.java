package com.aop.login.controller;

import com.aop.login.bean.UserBean;
import com.aop.login.service.UserService;
import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.catalina.connector.CoyoteOutputStream;
import org.apache.catalina.connector.OutputBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpServletResponse;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.ByteBuffer;


import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/getUsers")
    @ResponseBody
    public String getUsers(HttpServletResponse response) throws IOException {
        List<UserBean> list=userService.getUsers();


        OutputStream out=response.getOutputStream();
        CoyoteOutputStream coyoteOutputStream= (CoyoteOutputStream) out;
        String str=out.toString();
        Class<CoyoteOutputStream> clazz= CoyoteOutputStream.class;
        try {
           Field field= clazz.getDeclaredField("ob");
           if(field.getType().toString().endsWith("OutputBuffer")){
               field.setAccessible(true);//设置访问属性ob的权限
               OutputBuffer obObject= (OutputBuffer) field.get(coyoteOutputStream);//取得ob对象
               Class<OutputBuffer>clazz2=OutputBuffer.class;
               Field field2= clazz2.getDeclaredField("bb");
               if(field2.getType().toString().endsWith("ByteBuffer")){
                   field2.setAccessible(true);
                   ByteBuffer  heapByteBuffer= (ByteBuffer) field2.get(obObject);
                   Class<ByteBuffer> clazz3=ByteBuffer.class;
                   Field field3=clazz3.getDeclaredField("hb");
                       field3.setAccessible(true);
                       byte [] bytes= (byte[]) field3.get(heapByteBuffer);
                           String s=new String(bytes,"utf-8");
                           System.out.println(">>>>>>>>>>>>>>>>>>>>utf-8>>>>bytes:"+bytes+";>>>>str:"+s);

                   Map map=JSONObject.fromObject(s);
                   Set<String> keySet= map.keySet();
                   Iterator<String>it=keySet.iterator();
                   while(it.hasNext()){
                       String key=it.next();
                       System.out.println(">>>>>>>>>>>>"+key+"="+map.get(key));
                   }

                   String key=map.get("resultCode").toString();
                   String value=map.get("resultMsg").toString();

                   if(!"ok".equals(map.get("resultCode").toString())){
                       response.resetBuffer();
                      out.write(map.get("resultMsg").toString().getBytes());

                      out.flush();
                       return "";
                   }

               }
           }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(str);
        response.resetBuffer();
         out.write("OK".getBytes());
         out.flush();
         return "success";
    }
}
