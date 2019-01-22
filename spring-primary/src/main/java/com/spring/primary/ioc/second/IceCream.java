package com.spring.primary.ioc.second;

import org.springframework.stereotype.Component;

@Component
@Cold
@Cream
public class IceCream implements Dessert{
	@Override
	public void description() {
		System.out.println("IceCream");
	}
}
