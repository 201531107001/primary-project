package com.me.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.StudentDaoImpl;
import com.me.entity.Student;

@Service
public class StudentService {

	@Autowired
	StudentDaoImpl studentDao;
	
	public Student getById(int id) {
		return studentDao.getById(id);
	}
	
	public Student save(Student student){
		return studentDao.save(student);
	}
	
	public Student update(Student student){
		return studentDao.save(student);
	}
	
}
