package com.spring.primary.aop.first;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AspectMain {
	public static void main(String[] args){
		AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(AspectConfig.class);
		context.getBean(Performance.class).perform();
		// context.getBean(XiangShengPerformance.class).perform(); //错误方式获取bean
		
		System.out.println(context.containsBean("xiangShengPerformance"));
		
		context.close();
	}
}
