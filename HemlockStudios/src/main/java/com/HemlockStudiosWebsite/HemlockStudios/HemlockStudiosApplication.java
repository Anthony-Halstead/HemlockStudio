package com.HemlockStudiosWebsite.HemlockStudios;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.HemlockStudiosWebsite")
public class HemlockStudiosApplication {

	public static void main(String[] args) {
		SpringApplication.run(HemlockStudiosApplication.class, args);
	}
}
