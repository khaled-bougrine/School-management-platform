package com.projet_S2.clienEtudient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
@EnableFeignClients
@SpringBootApplication
public class ClienEtudientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClienEtudientApplication.class, args);
	}

}
