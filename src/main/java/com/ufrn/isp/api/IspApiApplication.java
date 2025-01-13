package com.ufrn.isp.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.ufrn.isp.api.config")
public class IspApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(IspApiApplication.class, args);
	}

}
