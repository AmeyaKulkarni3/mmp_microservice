package com.ameya.schedulemicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyMoviePlanScheduleMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMoviePlanScheduleMicroserviceApplication.class, args);
	}

}
