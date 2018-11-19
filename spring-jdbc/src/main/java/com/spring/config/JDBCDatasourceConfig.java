package com.spring.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.spring.data.StudentRepository;
import com.spring.data.impl.StudentRepositoryImpl;

@Configuration
@ComponentScan
public class JDBCDatasourceConfig {
	@Bean
	public DataSource dataSource(){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/gqm?useSSL=true");
		dataSource.setUsername("root");
		dataSource.setPassword("990219");
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource){
		return new JdbcTemplate(dataSource);
	}
	
	@Bean(name="studentRepository")
	public StudentRepositoryImpl studentRepository(JdbcTemplate jdbcTemplate){
		return new StudentRepositoryImpl(jdbcTemplate);
	}
}
