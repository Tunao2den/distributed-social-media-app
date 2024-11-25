package com.tuna.apigateway.filter;

import com.tuna.apigateway.model.data.ValidateTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private WebClient.Builder webClientBuilder;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
            if (routeValidator.isSecured.test(exchange.getRequest())) {
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("Authorization head is not found");
                }
                String token = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).get(0);
                String url = "http://auth-service/auth-service/validate-token";

                return webClientBuilder.build()
                        .post()
                        .uri(url)
                        .header(HttpHeaders.AUTHORIZATION, token)
                        .retrieve()
                        .bodyToMono(ValidateTokenResponse.class)
                        .flatMap(response -> {
                            if (!response.isValid()) {
                                return Mono.error(new RuntimeException("Token is not valid"));
                            }

                            ServerWebExchange updatedExchange = exchange.mutate()
                                    .request(request -> request.headers(headers -> headers.set("userName", response.getUserName())))
                                    .build();

                            return chain.filter(updatedExchange);
                        });
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {
    }
}
