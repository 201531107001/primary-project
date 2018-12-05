package com.me.data;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "teacherManagerFactory",
		transactionManagerRef = "teacherTransactionManager",
        basePackages= { "com.me.teacher.repository" }) //设置Repository所在位置
public class TeacherDataConfig {
	@Autowired
    private JpaProperties jpaProperties;

    @Bean(name = "teacherDataSource")
    @ConfigurationProperties(prefix = "teacher.datasource")
    public DataSource otherDataSource() {
        return new DruidDataSource();
    }
    
    @Bean(name = "teacherManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactory(
            EntityManagerFactoryBuilder builder,
            @Qualifier("teacherDataSource") DataSource dataSource) {
        return builder
                .dataSource(dataSource)
                .packages("com.me.teacher.entity")
                .properties(jpaProperties.getProperties())
                .persistenceUnit("test")
                .build();
    }
    
    @Bean(name = "teacherTransactionManager")
    public PlatformTransactionManager teacherTransactionManager(
            @Qualifier("teacherManagerFactory") EntityManagerFactory teacherManagerFactory) {
        return new JpaTransactionManager(teacherManagerFactory);
    }
}
