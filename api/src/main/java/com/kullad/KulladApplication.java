package com.kullad;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(value = {
		"com.kullad.controllers",
		"com.kullad.services",
		"com.kullad.config",
		"com.kullad.repositories"
})
public class KulladApplication {

	public static void main(String[] args) {
		Dotenv dotenv = Dotenv.load();
		System.setProperty("APPLICATION_NAME", dotenv.get("APPLICATION_NAME"));
		System.setProperty("PORT", dotenv.get("PORT"));
		System.setProperty("DEFAULT_API_PATH", dotenv.get("DEFAULT_API_PATH"));
		System.setProperty("DATABASE_URL", dotenv.get("DATABASE_URL"));
		System.setProperty("DATABASE_USERNAME", dotenv.get("DATABASE_USERNAME"));
		System.setProperty("DATABASE_PASSWORD", dotenv.get("DATABASE_PASSWORD"));
		System.setProperty("EMAIL_FROM", dotenv.get("EMAIL_FROM"));
		System.setProperty("EMAIL_PASSWORD", dotenv.get("EMAIL_PASSWORD"));
		System.setProperty("EMAIL_USERNAME", dotenv.get("EMAIL_USERNAME"));

		SpringApplication.run(KulladApplication.class, args);
	}

}
