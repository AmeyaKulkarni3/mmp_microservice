package com.ameya.theaterservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MyMoviePlanTheaterMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMoviePlanTheaterMicroserviceApplication.class, args);
	}

}
