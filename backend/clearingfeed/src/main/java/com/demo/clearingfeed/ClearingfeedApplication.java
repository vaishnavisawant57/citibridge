package com.demo.clearingfeed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.security.servlet.*;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ClearingfeedApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClearingfeedApplication.class, args);
		
		
       System.out.println("Runing");
	}

}
