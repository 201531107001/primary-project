package com.spring.primary.aop.third;

import org.springframework.stereotype.Component;

@Component
public class XiaoPingPerformance implements Performance{
	@Override
	public void perform() {
		System.out.println("相声表演");
	}
}
