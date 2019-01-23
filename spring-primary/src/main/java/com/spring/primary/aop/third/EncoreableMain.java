package com.spring.primary.aop.third;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class EncoreableMain {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(JavaConfig.class);
		Performance performance = context.getBean(Performance.class);
		performance.perform();
		((AdvertisementAble)performance).advertisement();
		context.close();
	}
}
