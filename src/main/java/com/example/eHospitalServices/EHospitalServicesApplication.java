package com.example.eHospitalServices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class EHospitalServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(EHospitalServicesApplication.class, args);
	}

}
