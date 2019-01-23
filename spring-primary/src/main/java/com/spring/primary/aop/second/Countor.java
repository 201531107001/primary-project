package com.spring.primary.aop.second;

import java.util.HashMap;
import java.util.Map;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
@Component
@Aspect
public class Countor {
	private Map<Integer, Integer> numberCount = new HashMap<Integer, Integer>();
	
	@Pointcut("execution(* com.spring.primary.aop.second.Numb.number(int))&&args(num)")
	public void number(int num){
		
	}
	
	@Before("number(num)")
	public void CountNumber(int num){
		int a = getCount(num);
		numberCount.put(num, a+1);
	}
	
	public int getCount(int num) {
		return numberCount.containsKey(num)? numberCount.get(num):0;
	}
}
