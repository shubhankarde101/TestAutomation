package com.demo.EmployeAPIs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication()
@EntityScan()
public class BootStrap implements CommandLineRunner {
	public static void main(String[] args) {
		SpringApplication.run(BootStrap.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Hello World");
	}
}
