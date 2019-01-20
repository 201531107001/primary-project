package com.me.sharding.jdbc.demo;

import com.me.sharding.jdbc.demo.entity.Student;
import com.me.sharding.jdbc.demo.services.StudentServices;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShardingDemoApplicationTests {

	@Autowired
	private StudentServices studentServices;

	@Test
	public void testGetAllStudents() {
		System.out.println(studentServices.getAllStudents());
	}
	
	@Test
    public void testGetAllStudentsByAge() {
        System.out.println(studentServices.getAllStudentsByAge(11));
    }
	
	@Test
    public void testInsert() {
//	    Student student = new Student("aaa",12);
//      studentServices.insert(student);
	    studentServices.insert(new Student("bbb", 11));
    }
	
	@Test
	public void testSelectByName() {
	    System.out.println(studentServices.getStudent("aaa"));
	}
}

