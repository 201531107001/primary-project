package com.me.moredatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.me")
@EntityScan("com.me.entity")
public class MoreDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoreDatabaseApplication.class, args);
	}
}
