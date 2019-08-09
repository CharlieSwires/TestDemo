package com.mongodb.TestDemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.BeanRepository;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses=BeanRepository.class)
public class TestDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestDemoApplication.class, args);
	}

}
