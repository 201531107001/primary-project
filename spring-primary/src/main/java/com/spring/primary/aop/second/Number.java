package com.spring.primary.aop.second;

import org.springframework.stereotype.Component;

@Component
public class Number implements Numb{
	public void number(int num){
		System.out.println("The number is "+num);
	}
}
