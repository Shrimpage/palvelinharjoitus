package com.palvelinohjelmointi.harj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.palvelinohjelmointi.harj.repository")
public class HarjApplication {

	public static void main(String[] args) {
		SpringApplication.run(HarjApplication.class, args);
	}
}
