package com.data.jpa.data.jpa.repository;

import com.data.jpa.data.jpa.bean.StudentBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentBean,Long> {
    @Query(value = "select t.* from student t where student_name=?1" ,nativeQuery = true)
    StudentBean getStudentByName(String studanetName);

    @Query(value = "select * from sutdent where student_name=:name",nativeQuery = true)
    StudentBean getStudentByName2(@Param("name")String studentName);


    /**
     * 这个有个jpa超级反人类的设计，本来自己根据主键查询，只显示一条，这里却把Object[] 当成多个实例对象的数据类似List<Object[])
     * （本意，是一个实例对象的多个属性）
     * 所以此处只能这样访问：
     *        Object[] objects= studentService.getUser(10024L);
     *        for(int i=0;i<objects.length;i++){
     *            Object []objs= (Object[]) objects[i];
     *            for(Object obj:objs){
     *                System.out.println(">>>>>>>>>>>"+obj.toString());
     *            }
     *        }
     * @param id
     * @return
     */
    @Query(value="select t.* from my_users t where user_id=?1",nativeQuery = true)
    Object [] getUser(Long id);

    @Query(value="select * from my_users ",nativeQuery = true)
    List<Object[]>getAllUsers();

    @Modifying
    @Query(value="update student set student_age=?1,student_name=?2 where sutdent_id=?3",nativeQuery = true)
    int update(Integer age,String name,Long id);
}
