package com.data.jpa.data.jpa.repository;

import com.data.jpa.data.jpa.bean.TeacherBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TeacherRepository extends JpaRepository<TeacherBean,Long> {

}
