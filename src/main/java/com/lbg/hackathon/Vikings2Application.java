package com.lbg.hackathon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Vikings2Application {

	public static void main(String[] args) {
		SpringApplication.run(Vikings2Application.class, args);
	}

}
