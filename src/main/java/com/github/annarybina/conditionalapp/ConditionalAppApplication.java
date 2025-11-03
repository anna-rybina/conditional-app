package com.github.annarybina.conditionalapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.github.annarybina")
public class ConditionalAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(ConditionalAppApplication.class, args);
	}
}
