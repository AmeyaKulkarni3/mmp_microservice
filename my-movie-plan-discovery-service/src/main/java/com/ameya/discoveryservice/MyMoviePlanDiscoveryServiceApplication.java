package com.ameya.discoveryservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MyMoviePlanDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyMoviePlanDiscoveryServiceApplication.class, args);
	}

}
