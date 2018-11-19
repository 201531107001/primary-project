package com.spring.service;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AccountMoneyServiceTest {
	
	public static AnnotationConfigApplicationContext context; 
	@BeforeClass
	public static void beforeClass() {
		//System.out.println("beforeClass");
		context = new AnnotationConfigApplicationContext(com.spring.config.DBCPDatasourceConfig.class);
	}
	
	@Test
	public void testAccount() {
		AccountMoneyService service = context.getBean(AccountMoneyService.class);
		service.account("gqm", "gql", 200);
	}
	
	@Test
	public void testTransactionAccount() {
		AccountMoneyService service = context.getBean(AccountMoneyService.class);
		service.transactionAccount("gqm", "gql", 200);
	}

}
