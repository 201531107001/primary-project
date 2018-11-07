package com.me.dao;

import org.springframework.stereotype.Component;

import com.me.entity.Student;

@Component
public class StudentDaoImpl implements StudentDao{

	public Student getById(int id) {
		
		if(id == 1)
			return new Student(1, "gqm", 22, "1999-02-19");
		else
			return null;
	}

	public Student saveStudent(Student student) {
		student.setId(2);
		
		return student;
	}

}
