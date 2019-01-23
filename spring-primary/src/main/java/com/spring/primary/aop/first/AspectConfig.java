package com.spring.primary.aop.first;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
@ComponentScan
@Configuration
@EnableAspectJAutoProxy(exposeProxy=true)
public class AspectConfig {
	
}
