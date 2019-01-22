package com.spring.primary.ioc.second;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class DessertMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(DessertConfig.class);
		PeopleBuyDessert pDessert = context.getBean(PeopleBuyDessert.class);
		pDessert.getDessert().description();
		context.close();
	}
}
