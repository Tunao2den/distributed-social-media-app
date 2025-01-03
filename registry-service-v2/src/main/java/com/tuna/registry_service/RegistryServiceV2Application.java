package com.tuna.registry_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class RegistryServiceV2Application {

	public static void main(String[] args) {
		SpringApplication.run(RegistryServiceV2Application.class, args);
	}

}
