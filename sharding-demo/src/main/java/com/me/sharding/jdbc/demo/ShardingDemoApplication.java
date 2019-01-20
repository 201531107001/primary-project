package com.me.sharding.jdbc.demo;

import com.me.sharding.jdbc.demo.entity.Student;
import com.me.sharding.jdbc.demo.services.StudentServices;
import com.me.sharding.jdbc.demo.utils.ApplicationContextProvider;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan
@MapperScan("com.me.sharding.jdbc.demo.mapper")
public class ShardingDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShardingDemoApplication.class, args);

//		ApplicationContext context = ApplicationContextProvider.getApplicationContext();
//		StudentServices studentServices = context.getBean(StudentServices.class);
//		Student student = new Student("aaa",12);
//		studentServices.insert(student);
//		System.out.println(studentServices.getAllStudents());
	}

}

