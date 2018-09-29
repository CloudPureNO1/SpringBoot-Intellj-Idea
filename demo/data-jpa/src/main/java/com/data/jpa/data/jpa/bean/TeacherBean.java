package com.data.jpa.data.jpa.bean;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Objects;
@Component
@Entity(name="TeacherBean")   //标识pojo 为jpa实体
@Table(name = "teacher")     //标识 pojo 对应的表，如果没有默认为 表明和pojo的名称一致
@SequenceGenerator(name="teacherId",sequenceName = "squ_teacher222",allocationSize=1,initialValue=1)
public class TeacherBean implements  java.io.Serializable{

    @Id             //主键标识
    @Column(name = "teacher_id",unique = true,nullable = false,length = 64) //列属性，如果没有配置，列明默认与属性名一致
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "teacherId")
    private Long teacherId;
    @Column(name = "teacher_name")
    private String teacherName;
    @Column(name="teacher_age")
    private Integer teacherAge;

    private String mak;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeacherBean that = (TeacherBean) o;
        return Objects.equals(teacherId, that.teacherId) &&
                Objects.equals(teacherName, that.teacherName) &&
                Objects.equals(teacherAge, that.teacherAge) &&
                Objects.equals(mak, that.mak);
    }

    @Override
    public int hashCode() {

        return Objects.hash(teacherId, teacherName, teacherAge, mak);
    }

    @Override
    public String toString() {
        return "TeacherBean{" +
                "teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", teacherAge=" + teacherAge +
                ", mak='" + mak + '\'' +
                '}';
    }

    public TeacherBean() {
    }

    public TeacherBean(Long teacherId, String teacherName, Integer teacherAge, String mak) {

        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.teacherAge = teacherAge;
        this.mak = mak;
    }


    public Long getTeacherId() {

        return teacherId;
    }

    public void setTeacherId(Long teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    public String getMak() {
        return mak;
    }

    public void setMak(String mak) {
        this.mak = mak;
    }
}
