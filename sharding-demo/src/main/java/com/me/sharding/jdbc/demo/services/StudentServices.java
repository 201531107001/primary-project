package com.me.sharding.jdbc.demo.services;

import com.me.sharding.jdbc.demo.entity.Student;
import com.me.sharding.jdbc.demo.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServices {
	@Autowired
	private StudentMapper studentMapper;

	public List<Student> getAllStudents(){
		return studentMapper.selectAllStudent();
	}
	
	public List<Student> getAllStudentsByAge(Integer age){
        return studentMapper.selectAllStudentByAge(age);
    }

	public Student getStudent(String name){
		return studentMapper.selectByPrimaryKey(name);
	}

	public void insert(Student student){
		studentMapper.insert(student);
	}
}