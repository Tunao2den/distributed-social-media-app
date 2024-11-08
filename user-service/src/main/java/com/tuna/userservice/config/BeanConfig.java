package com.tuna.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class BeanConfig {
    @Bean
    //@LoadBalanced -> todo: make api call dynamic by eureka
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
