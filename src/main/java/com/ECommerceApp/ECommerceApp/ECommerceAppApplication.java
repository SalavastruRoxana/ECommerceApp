package com.ECommerceApp.ECommerceApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ECommerceApp.ECommerceApp.repository")
public class ECommerceAppApplication //extends SpringBootServletInitializer
{

	public static void main(String[] args) {
		SpringApplication.run(ECommerceAppApplication.class, args);
	}


}