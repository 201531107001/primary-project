package com.spring.service2;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AccountMoneyServiceByStateTest {
	public static AnnotationConfigApplicationContext context; 
	@BeforeClass
	public static void beforeClass() {
		//System.out.println("beforeClass");
		context = new AnnotationConfigApplicationContext(com.spring.config.DBCPDatasourceConfig.class);
	}
	
	@Test
	public void testAccount() {
		AccountMoneyServiceByState serviceByState = context.getBean(AccountMoneyServiceByState.class);
		serviceByState.account("gql", "gqm", 100);
	}
	
}
