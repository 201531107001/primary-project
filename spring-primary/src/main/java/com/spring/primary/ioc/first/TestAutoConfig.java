package com.spring.primary.ioc.first;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class TestAutoConfig {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = 
				new AnnotationConfigApplicationContext(CDPlayConfig.class);
		CDPlayer player = context.getBean(CDPlayer.class);
		player.play();
		context.close();
	}
	
	
}
