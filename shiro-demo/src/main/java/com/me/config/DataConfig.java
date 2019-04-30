package com.me.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;


@Configuration
public class DataConfig {
	@Bean
	public DataSource dataSourceDev() {
		return new EmbeddedDatabaseBuilder()
				.setType(EmbeddedDatabaseType.H2)
				.addScript("classpath:schema.sql")
				.addScript("classpath:data.sql").build();
	}
	
	@Bean
	public  JdbcTemplate getJdbcTemplate(DataSource dataSourde) {
		return new JdbcTemplate(dataSourde);
	}
}
