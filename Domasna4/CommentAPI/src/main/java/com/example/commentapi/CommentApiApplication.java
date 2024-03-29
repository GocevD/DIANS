package com.example.commentapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CommentApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CommentApiApplication.class, args);
	}

}
