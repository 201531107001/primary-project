package com.spring.primary.aop.third;

import org.springframework.stereotype.Component;

@Component
public class AdvertisementAbleImpl implements AdvertisementAble{
	@Override
	public void advertisement() {
		System.out.println("插入广告:今年过节不收礼，收礼只收脑白金");
	}
}
