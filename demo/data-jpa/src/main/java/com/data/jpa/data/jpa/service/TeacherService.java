package com.data.jpa.data.jpa.service;

import com.data.jpa.data.jpa.bean.TeacherBean;

import java.util.List;

public interface TeacherService {
    Long addTeacher(TeacherBean bean);
    void deleteTeacher(TeacherBean bean);
    List<TeacherBean> getAllTeacher();
}
