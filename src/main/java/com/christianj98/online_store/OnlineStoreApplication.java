package com.christianj98.online_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class OnlineStoreApplication {
	public static void main(String[] args) {
		SpringApplication.run(OnlineStoreApplication.class, args);
	}
}
