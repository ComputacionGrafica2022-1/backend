package com.aaa.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableAsync
public class BackendApplication {



	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
		
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
			return new WebMvcConfigurer() {
					@Override
					public void addCorsMappings(CorsRegistry registry) {
							registry.addMapping("/**")
									// .allowedOrigins("http://localhost:3000", "https://cg-aaa.herokuapp.com")
									.allowedOrigins("*")
									.allowedMethods("*");
					}

			};
	}

}
