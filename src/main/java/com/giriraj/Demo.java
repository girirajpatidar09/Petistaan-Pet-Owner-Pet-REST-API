package com.giriraj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;


@OpenAPIDefinition(
		info = @Info(
				title = "Petistaan",
		        version = "1.0.0",
		        description = "Documentation for Petistaan application.",
		        contact = @Contact(
		            name = "Giriraj Patidar",
		            url = "https://github.com/",
		            email = "girirajpatidar677@gmail.com"
		        )
				),
		servers = {
				@Server(url = "http://localhost:8080", description = "Development Server")
		})
@PropertySource("classpath:messages.properties")
@SpringBootApplication
public class Demo {

	public static void main(String[] args) {
		SpringApplication.run(Demo.class, args);
	}

}