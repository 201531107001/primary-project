package com.spring.primary.aop.first;

import org.springframework.stereotype.Component;

@Component
public class XiangShengPerformance implements Performance{
	@Override
	public void perform() {
		System.out.println("相声表演");
	}
}
