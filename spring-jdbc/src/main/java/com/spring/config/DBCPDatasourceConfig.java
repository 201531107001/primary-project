package com.spring.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import com.spring.data.PeopleRepository;
import com.spring.data.impl.PeopleRepositoryImpl;
import com.spring.data.impl.StudentRepositoryImpl;
import com.spring.service.AccountMoneyService;
import com.spring.service2.AccountMoneyServiceByState;
import org.springframework.transaction.TransactionDefinition;

@Configuration
@EnableTransactionManagement // 自动代理事务（声明式事务处理）
@PropertySource("classpath:/jdbc.properties")
public class DBCPDatasourceConfig {
	@Autowired
	Environment evn;

	@Bean
	@Profile("prod")
	public DataSource dataSourceProd() {
		 System.out.println("prod");
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(evn.getProperty("dbcp.driverClassName"));
		dataSource.setUrl(evn.getProperty("dbcp.url"));
		dataSource.setUsername(evn.getProperty("dbcp.username"));
		dataSource.setPassword(evn.getProperty("dbcp.password"));
		dataSource.setInitialSize(evn.getProperty("dbcp.InitialSize", Integer.class));
		dataSource.setMaxIdle(10);
		dataSource.setMinIdle(5);
		return dataSource;
	}

	@Bean
	@Profile("dev")
	public DataSource dataSourceDev() {
		 System.out.println("dev");
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql")
				.addScript("classpath:test-data.sql").build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	@Bean(name = "studentRepository")
	public StudentRepositoryImpl studentRepository(JdbcTemplate jdbcTemplate) {
		return new StudentRepositoryImpl(jdbcTemplate);
	}

	@Bean
	public PeopleRepository peopleRepository(JdbcTemplate jdbcTemplate) {
		return new PeopleRepositoryImpl(jdbcTemplate);
	}

	@Bean // 编程式事务处理
	public AccountMoneyService accountMoneyService(PeopleRepository peopleRepository, TransactionTemplate template) {
		return new AccountMoneyService(peopleRepository, template);
	}

	// 事务模板(编程式事务处理)
	@Bean
	public TransactionTemplate transactionTemplate(DataSourceTransactionManager manager) {
		return new TransactionTemplate(manager);
	}
	
	//--------------------------------------------------------------
	@Bean // 声明式事务处理
	public AccountMoneyServiceByState accountMoneyServiceByState(PeopleRepository peopleRepository) {
		return new AccountMoneyServiceByState(peopleRepository);
	}

	// 事务处理管理
	@Bean
	public DataSourceTransactionManager dataSourceTransactionManager(DataSource dataSource) {
		DataSourceTransactionManager transactionManager = new DataSourceTransactionManager(dataSource);
		return transactionManager;
	}

}
