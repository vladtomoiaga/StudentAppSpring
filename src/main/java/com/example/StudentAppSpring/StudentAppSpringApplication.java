package com.example.StudentAppSpring;

import com.example.StudentAppSpring.dao.AppDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StudentAppSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentAppSpringApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {
		return runner -> {
			appDAO.printActions();
			appDAO.action();
		};
	}

}
