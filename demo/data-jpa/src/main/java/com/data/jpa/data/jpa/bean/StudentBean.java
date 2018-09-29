package com.data.jpa.data.jpa.bean;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.GenericGenerators;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Table(name = "student")

@Setter
@Getter
@SequenceGenerator(name="stu_squ",sequenceName = "squ_student",initialValue = 777,allocationSize = 1)
public class StudentBean implements java.io.Serializable {
    @Id
    @Column(name="sutdent_id",nullable = false,unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "stu_squ")
    private Long studentId;

    @Column(name="student_name")
    private String studentName;
    @Column(name="student_age")
    private Integer studentAge;
}
