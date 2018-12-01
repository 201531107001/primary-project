package com.me.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.entity.Student;
import com.me.repository.StudentRepository;

@Service
public class StudentService {
	
	@Autowired
	private StudentRepository studentRepository;
	
	public List<Student> getAllStudent() {
		return studentRepository.getAllStudent();
	}
	
	public Student getStudentByName(String username) {
		return studentRepository.getStudentByName(username);
	}
}
