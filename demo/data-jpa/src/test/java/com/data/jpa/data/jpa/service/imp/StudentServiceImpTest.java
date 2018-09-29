package com.data.jpa.data.jpa.service.imp;

import com.data.jpa.data.jpa.bean.StudentBean;
import com.data.jpa.data.jpa.service.StudentService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringRunner.class)
@SpringBootTest
public class StudentServiceImpTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentBean studentBean;
    @Test
    public void getStudentByName() {

        Assert.assertEquals("Jack",studentService.getStudentByName("Jack").getStudentName());
    }

    @Test
    public void addStudent(){
        studentBean.setStudentName("Jack");
        studentBean.setStudentAge(23);
        Assert.assertEquals(Long.valueOf(777),studentService.addStudent(studentBean).getStudentId());
    }

    @Test
    public void getUser() {
       Object[] objects= studentService.getUser(10024L);
       for(int i=0;i<objects.length;i++){
           Object []objs= (Object[]) objects[i];
           for(Object obj:objs){
               System.out.println(">>>>>>>>>>>"+obj.toString());
           }
       }
    }

    @Test
    public void getAllUsers() {
       List<Object[]> list= studentService.getAllUsers();
       Assert.assertEquals(7,list.size());
       for(Object[] objs:list){
           String str="";
           for(Object obj:objs){
              str+=obj.toString();
           }
           System.out.println(">>>>>>>>>>>>>>>"+str);
       }
    }

    @Test
    public void update() {

        studentService.update(11,"wxy",778L);
    }
}