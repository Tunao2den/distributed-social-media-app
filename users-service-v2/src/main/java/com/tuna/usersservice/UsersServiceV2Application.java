package com.tuna.usersservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UsersServiceV2Application {

    public static void main(String[] args) {
        SpringApplication.run(UsersServiceV2Application.class, args);
    }

}
