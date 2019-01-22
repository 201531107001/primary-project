package com.spring.primary.ioc.second;

import org.springframework.stereotype.Component;

@Component
@Soft
@Cream
public class Cookies implements Dessert{
	@Override
	public void description() {
		System.out.println("Cookies");
	}
}
