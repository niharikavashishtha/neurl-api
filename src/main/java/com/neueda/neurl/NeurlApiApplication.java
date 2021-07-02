package com.neueda.neurl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NeurlApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(NeurlApiApplication.class, args);
	}
}
