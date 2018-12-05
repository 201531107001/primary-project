package com.me.moredatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.me")
public class MoreDatabaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MoreDatabaseApplication.class, args);
	}
}
