package com.me.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "studentManagerFactory",
        basePackages= { "com.me.student.repository" }) //设置Repository所在位置
public class StudentDataConfig {
	@Autowired
    private JpaProperties jpaProperties;

    @Primary
    @Bean(name = "studentDataSource")
    @ConfigurationProperties(prefix = "student.datasource")
    public DataSource dataSource() {
        return new DruidDataSource();
    }
    
    @Primary
    @Bean(name = "studentManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("studentDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.me.student.entity")
                .properties(jpaProperties.getProperties())
                .persistenceUnit("test")
                .build();
    }
}
