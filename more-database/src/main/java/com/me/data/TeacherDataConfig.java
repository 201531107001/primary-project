package com.me.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableJpaRepositories(
		entityManagerFactoryRef = "teacherManagerFactory",
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
}
