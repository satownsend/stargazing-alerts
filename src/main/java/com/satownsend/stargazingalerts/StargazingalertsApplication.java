package com.satownsend.stargazingalerts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class StargazingalertsApplication {

	public static void main(String[] args) {
		SpringApplication.run(StargazingalertsApplication.class, args);
	}
}
