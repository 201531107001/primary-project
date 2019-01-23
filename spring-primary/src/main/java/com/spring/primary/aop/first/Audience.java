package com.spring.primary.aop.first;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Audience {
	@Pointcut("execution(* com.spring.primary.aop.first.Performance.perform(..))")
	public void performance(){	}
	
	@Before("performance()")
	public void beforePerform(){
		System.out.println("表演前1");
	}
	
	@Before("performance()")
	public void takeSits(){
		System.out.println("观众坐到自己的位置1");
	}
	
	@AfterReturning("performance()")
	public void afterPerform(){
		System.out.println("表演结束，观众退出1");
	}
	
	@Around("performance()")
	public void aroundPerform(ProceedingJoinPoint jp){
		try {
			System.out.println("表演前2");
			System.out.println("观众坐到自己的位置2");
			jp.proceed();
			System.out.println("表演结束，观众退出2");
		} catch (Throwable e) {
		}
	}
}
