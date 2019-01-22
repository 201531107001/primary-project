package com.spring.primary.ioc.third;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BookMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(JavaConfig.class);
		Book book = context.getBean(Book.class);
		People people = context.getBean(People.class);
		Student student = context.getBean(Student.class);
		System.out.println(book.getAuthor()+"   "+book.getTitle());
		System.out.println(people.getName()+"   "+people.getAge());
		System.out.println(student.getName()+"   "+student.getAddress());
		context.close();
	}
}
