package com.spring.primary.ioc.second;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
//@Primary
@Soft
public class Cake implements Dessert{
	@Override
	public void description() {
		System.out.println("Cake");
	}
}
