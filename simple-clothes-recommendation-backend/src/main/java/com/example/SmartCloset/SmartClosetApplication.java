package com.example.SmartCloset;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class SmartClosetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartClosetApplication.class, args);
	}

}
