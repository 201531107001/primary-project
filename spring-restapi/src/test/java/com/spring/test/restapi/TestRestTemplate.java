package com.spring.test.restapi;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.me.entity.Student;

/**
 * Ê¹ÓÃspringµÄRestTemplate
 * @author gqm
 *
 */
public class TestRestTemplate {
	String url = "http://127.0.0.1:8080/spring-restapi/student/";
	
	@Test
	public  void testGet() {
		RestTemplate client = new RestTemplate();
		Map<String, Object> param = new HashMap<String, Object>(1);
		param.put("id", 1);
		Student student = client.getForObject(url+"get/{id}", Student.class,param);
		System.out.println(student.getName());
	}
	
	@Test
	public  void testPost() {
		RestTemplate client = new RestTemplate();
		
		Student student = new Student("gqm", 19, "1999-02-19");
		student = client.postForObject(url+"save",student , Student.class);
		System.out.println(student.getName());
	}
	
}
