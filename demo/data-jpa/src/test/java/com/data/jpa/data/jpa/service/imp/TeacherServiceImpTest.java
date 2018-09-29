package com.data.jpa.data.jpa.service.imp;

import com.data.jpa.data.jpa.bean.TeacherBean;
import com.data.jpa.data.jpa.service.TeacherService;
import org.codehaus.xfire.spring.SpringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeacherServiceImpTest {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private TeacherBean teacherBean;

    @Before
    public void before(){
         teacherBean.setTeacherName("Jack");
         teacherBean.setTeacherAge(33);
         teacherBean.setMak("This is a English teacher!");
    }

    @Test
    public void addTeacher() {
       Long tId= teacherService.addTeacher(teacherBean);
       System.out.println(">>>>>>>>>>>>>>>>tId=="+tId);
    }

    @Test
    public void deleteTeacher() {
        teacherService.deleteTeacher(teacherBean);
    }

    @Test
    public void getAllTeacher() {
        List<TeacherBean>list=teacherService.getAllTeacher();
        System.out.println(">>>>>>>>>>>>>>>>"+list.toString());
    }
}