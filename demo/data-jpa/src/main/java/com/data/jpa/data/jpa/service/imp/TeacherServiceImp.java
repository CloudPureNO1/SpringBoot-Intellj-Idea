package com.data.jpa.data.jpa.service.imp;

import com.data.jpa.data.jpa.bean.TeacherBean;
import com.data.jpa.data.jpa.repository.TeacherRepository;
import com.data.jpa.data.jpa.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public Long addTeacher(TeacherBean bean) {
        bean=teacherRepository.save(bean);
        return bean.getTeacherId();
    }

    @Override
    public void deleteTeacher(TeacherBean bean) {
        teacherRepository.delete(bean);
    }

    @Override
    public List<TeacherBean> getAllTeacher() {
        List<TeacherBean> list=teacherRepository.findAll();
        return list;
    }
}
