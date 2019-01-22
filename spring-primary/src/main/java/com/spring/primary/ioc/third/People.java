package com.spring.primary.ioc.third;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class People {
	private String name;
	private String age;
	
	//通过systemProperties对象引用系统属性
	public People(@Value("#{systemProperties['sun.desktop']}") String name,
			@Value("#{systemProperties['people.age']}") String age) {
		this.age = age;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
	
	
}
