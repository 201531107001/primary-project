package com.spring.primary.ioc.second;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class PeopleBuyDessert {
	private Dessert dessert;

	public Dessert getDessert() {
		return dessert;
	}
	
	@Autowired
	//@Qualifier("cake")
	@Cold
	@Cream
	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}
	
}
