package com.spring.primary.ioc.third;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
@PropertySource(value = "classpath:com/spring/primary/ioc/third/book.properties",encoding="utf-8")
public class JavaConfig {
    @Autowired
    Environment evn;
    
    // 解析属性占位符需要这个bean
	@Bean
	public static PropertySourcesPlaceholderConfigurer	placeholderConfigurer(){  
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public Student getStudent() {
	    return new Student(evn.getProperty("student.name"), evn.getProperty("student.address"));
	}
}
