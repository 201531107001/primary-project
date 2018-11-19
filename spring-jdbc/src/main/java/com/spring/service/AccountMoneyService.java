package com.spring.service;

import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import com.spring.data.PeopleRepository;
/**
 * 编程式事务处理
 * @author 郭清明
 *
 */
public class AccountMoneyService {

	private PeopleRepository peopleRepository;
	private TransactionTemplate transactionTemplate;

	public AccountMoneyService(PeopleRepository peopleRepository, TransactionTemplate transactionTemplate) {
		this.peopleRepository = peopleRepository;
		this.transactionTemplate = transactionTemplate;
	}

	public void account(String out, String in, double money) {
		peopleRepository.sub(out, money);
		int a = 1 / 0;
		peopleRepository.add(in, money);
	}
	
	public void transactionAccount(final String out, final String in,final double money){
		transactionTemplate.execute(new TransactionCallbackWithoutResult() {
			
			@SuppressWarnings("unused")
			@Override
			protected void doInTransactionWithoutResult(TransactionStatus arg0) {
				peopleRepository.sub(out, money);
				int a = 1 / 0;
				a++;
				peopleRepository.add(in, money);
			}
		});
	}
}
