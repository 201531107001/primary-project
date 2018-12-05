package com.me.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.me.moredatabase.MoreDatabaseApplication;
import com.me.student.repository.StudentRepository;
import com.me.teacher.repository.TeacherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=MoreDatabaseApplication.class)
public class StudentTeacherRepositoryTest {

	@Autowired
	TeacherRepository teacherRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Test
	public void getAllStudentAndTeacher() {
		System.out.println(teacherRepository.findAll());
		System.out.println(studentRepository.findAll());
	}
}
