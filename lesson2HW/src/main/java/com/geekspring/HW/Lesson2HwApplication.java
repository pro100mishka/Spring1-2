package com.geekspring.HW;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class Lesson2HwApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lesson2HwApplication.class, args);
	}

}
