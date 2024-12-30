package com.tuna.api_gateway;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GatewayConfig {

    @LoadBalanced
    @Bean
    WebClient.Builder loadBalanced() {
        return WebClient.builder();
    }

    @Primary
    @Bean
    WebClient.Builder webClient() {
        return WebClient.builder();
    }
//
//    @Bean
//    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
//        return builder.routes()
////                .route("users-service-v2", route -> route
////                        .path("/users-service-v2/**") // Predicate
////                        .uri("http://localhost:8100")) // Destination URI
//                .route(r -> r.path("users-service-v2/**") //users-service-v2
//                        .uri("http://localhost:8100")
//                )
//                .build();
//    }
}
