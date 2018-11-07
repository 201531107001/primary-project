package com.student.api;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.me.dao.StudentDaoImpl;
import com.me.entity.Student;
import com.me.error.MyError;
import com.me.exception.StudentNotFoundException;


@RestController
@RequestMapping("/student")
public class StudentController {
	
	@Autowired
	StudentDaoImpl studentDaoImpl; 
	
	@GetMapping(value="/get/{id}")
	@ResponseBody
	public Student getStudent(@PathVariable int id){
		
		System.out.println(id);
		
		Student student = studentDaoImpl.getById(id);
		if(student == null) {
			throw new StudentNotFoundException(id);
		}
		return student;
	}
	
	@PostMapping(value="/save",produces="application/json")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student,UriComponentsBuilder ucb){
		student = studentDaoImpl.saveStudent(student);
		System.out.println(student);
		HttpHeaders headers = new HttpHeaders();
		URI uri = ucb.path("/student/get/"+student.getId()).build().toUri();
		headers.setLocation(uri);
		
		return new ResponseEntity<Student>(student, headers, HttpStatus.CREATED);
	}
	
	@ExceptionHandler(StudentNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public MyError studentNotFound(StudentNotFoundException e){
		int id = e.getId();
		MyError error = new MyError(404,"student not found,please check your id:"+id);
		return error;
	}
}
