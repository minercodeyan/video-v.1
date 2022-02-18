package com.example.vidT;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableJpaRepositories
@SpringBootApplication
public class VidTApplication {
	public static void main(String[] args) {
		SpringApplication.run(VidTApplication.class, args);
	}
}
