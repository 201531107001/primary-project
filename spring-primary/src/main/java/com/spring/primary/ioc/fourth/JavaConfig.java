package com.spring.primary.ioc.fourth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:com/spring/primary/ioc/third/book.properties")
public class JavaConfig {

    @Bean
    @Conditional(StudentCondition.class)
    public People student() {
        return new Student();
    }
    
    @Bean
    @Conditional(TeacherCondition.class)
    public People teacher() {
        return new Teacher();
    }
}
