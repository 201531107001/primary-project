package com.spring.primary.ioc.fourth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = 
                new AnnotationConfigApplicationContext(JavaConfig.class);
        People people = context.getBean(People.class);
        System.out.println(people.getDescribe());
        context.close();
    }
}
