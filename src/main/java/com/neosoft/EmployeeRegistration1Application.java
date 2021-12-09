package com.neosoft;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages={"com.neosoft"})
@EnableJpaRepositories("com.neosoft.repository")
@EntityScan("com.neosoft.entity")
public class EmployeeRegistration1Application {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeRegistration1Application.class, args);
	}

}
