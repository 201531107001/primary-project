package com.spring.data.impl;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.spring.data.StudentRepository;
import com.spring.entity.Student;

public class StudentRepositoryImplTest {
	public static AnnotationConfigApplicationContext context; 
	@BeforeClass
	public static void beforeClass() {
		//System.out.println("beforeClass");
		context = new AnnotationConfigApplicationContext(com.spring.config.DBCPDatasourceConfig.class);
	}

	@Before
	public void before() {
		//System.out.println("before");
	}

	@Test
	public void testAdd() {
		System.out.println("testAdd");
		Student student = new Student();
		student.setName("郭清明");
		student.setPassword("990219");
		StudentRepository studentRepository = (StudentRepository)context.getBean("studentRepository");
		System.out.println(context.containsBean("studentRepository"));
		
		studentRepository.add(student);
	}
	
	@Test
	public void testUpdate() {
		System.out.println("testUpdate");
		Student student = new Student();
		student.setName("123");
		student.setPassword("990219");
		StudentRepository studentRepository = (StudentRepository)context.getBean("studentRepository");
		studentRepository.update(student);
	}
	
	@Test
	public void testQuery() {
		System.out.println("testQuery");
		Student student;
		StudentRepository studentRepository = (StudentRepository)context.getBean("studentRepository");
		student = studentRepository.query("gql");
		System.out.println(student.getPassword());
	}

	@After
	public void after() {
		//System.out.println("after");
	}

	@AfterClass
	public static void afterClass() {
		//System.out.println("afterClass");
		context.close();
	}
}
