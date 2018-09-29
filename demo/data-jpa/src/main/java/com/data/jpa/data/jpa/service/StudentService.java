package com.data.jpa.data.jpa.service;

import com.data.jpa.data.jpa.bean.StudentBean;

import java.util.List;

public interface StudentService  {
    StudentBean getStudentByName(String studanetName);
    StudentBean getStudentByName2(String studanetName);

    Object [] getUser(Long id);

    List<Object[]> getAllUsers();
    StudentBean addStudent(StudentBean studentBean);
    Integer update(Integer age,String name,Long id);
}
