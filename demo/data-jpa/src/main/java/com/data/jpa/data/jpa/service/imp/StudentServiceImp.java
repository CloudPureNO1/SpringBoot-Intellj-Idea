package com.data.jpa.data.jpa.service.imp;

import com.data.jpa.data.jpa.bean.StudentBean;
import com.data.jpa.data.jpa.repository.StudentRepository;
import com.data.jpa.data.jpa.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentServiceImp implements StudentService {
    @Resource
    private StudentRepository studentRepository;
    @Override
    public StudentBean getStudentByName(String studanetName) {
        return studentRepository.getStudentByName(studanetName);
    }
    @Override
    public StudentBean getStudentByName2(String studanetName) {
        return studentRepository.getStudentByName2(studanetName);
    }

    @Override
    public Object[] getUser(Long id) {
        Object[] obj=studentRepository.getUser(id);
        return obj ;
    }

    @Override
    public List<Object[]> getAllUsers() {
        return studentRepository.getAllUsers();
    }

    @Override
    @Transactional
    public StudentBean addStudent(StudentBean studentBean) {
        return studentRepository.save(studentBean);
    }

    @Override
    @Transactional
    public Integer update(Integer age,String name,Long id) {
        return studentRepository.update(age,name,id);
    }
}
