package com.me.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes=com.me.data.DataConfig.class,loader=AnnotationConfigContextLoader.class)
public class StudentServiceTest {

	@Autowired
	StudentService studentService;
	
	@Test
	public void testGetAllStudent() {
		System.out.println(studentService.getAllStudent());
	}
	
	@Test
	public void testGetStudentByNAme() {
		System.out.println(studentService.getStudentByName("gqm"));
	}

}
