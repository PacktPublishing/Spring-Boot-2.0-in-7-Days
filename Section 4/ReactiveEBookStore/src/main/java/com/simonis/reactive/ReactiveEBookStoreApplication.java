package com.simonis.reactive;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;

@SpringBootApplication
public class ReactiveEBookStoreApplication {
	static final Logger LOG = LoggerFactory
			.getLogger(ReactiveEBookStoreApplication.class);
	
	public static void main(String[] args) throws InterruptedException {
		SpringApplication.run(ReactiveEBookStoreApplication.class, args);
		
	
	}
	
	@Bean
	WebClient webClient() {
		return WebClient
			.create("http://localhost:8080")
			.mutate()
			.build();
	}
}
