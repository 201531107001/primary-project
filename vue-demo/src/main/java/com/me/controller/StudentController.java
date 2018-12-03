package com.me.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.entity.Student;
import com.me.service.StudentService;

 
@RestController
@RequestMapping("/student")
public class StudentController {
    
	@Autowired
	private StudentService studentService;

	@GetMapping("/all")
	public List<Student> getAllStudent(){
		return studentService.getAllStudent();
	}

	@GetMapping("/{username}")
	public Student getStudentByName(@PathVariable String username) {
		return studentService.getStudentByName(username);
	}
	
	@PostMapping(value="/add",produces="application/json")
	public int addStudent(@RequestBody Student student) {
	    return studentService.addStudent(student);
	}
}
