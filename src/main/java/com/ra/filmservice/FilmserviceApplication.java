package com.ra.filmservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class FilmserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(FilmserviceApplication.class, args);
	}
}
