package com.spring.primary.aop.second;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ParamerMain {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(JavaConfig.class);
		Numb number = context.getBean(Numb.class);
		number.number(1);
		number.number(1);
		number.number(1);
		number.number(3);
		number.number(3);
		number.number(4);
		number.number(20);
		number.number(2);
		Countor countor = context.getBean(Countor.class);
		System.out.println(countor.getCount(1));
		context.close();
	}
}
