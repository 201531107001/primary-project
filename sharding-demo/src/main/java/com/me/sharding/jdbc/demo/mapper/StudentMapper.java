package com.me.sharding.jdbc.demo.mapper;

import com.me.sharding.jdbc.demo.entity.Student;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {
    int deleteByPrimaryKey(String name);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String name);

    List<Student> selectAllStudent();
    
    List<Student> selectAllStudentByAge(Integer age);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}