package com.example.validationworkflow;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling

public class ValidationWorkflowApplication {

	public static void main(String[] args) {
		SpringApplication.run(ValidationWorkflowApplication.class, args);
	}

}
