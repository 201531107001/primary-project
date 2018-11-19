package com.spring.service2;

import org.springframework.transaction.annotation.Transactional;

import com.spring.data.PeopleRepository;
/**
 * 声明式事务管理，基于AOP
 * @author 清明
 *
 */

public class AccountMoneyServiceByState {
	private PeopleRepository peopleRepository;
	
	public AccountMoneyServiceByState(PeopleRepository peopleRepository){
		this.peopleRepository = peopleRepository;
	}
	
	@Transactional
	public void account(String out,String in,double money){
		peopleRepository.sub(out, money);
		int a = 1/0;
		peopleRepository.add(in, money);
	}
}
