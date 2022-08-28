package com.github.javamultiplex.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.RSocketGraphQlClient;

/**
 * @author Rohit Agarwal on 28/08/22 3:26 pm
 * @copyright github.com/javamultiplex
 */
@Configuration
public class RSocketClientConfig {
    @Bean
    public RSocketGraphQlClient rSocketGraphQlClient() {
        return RSocketGraphQlClient.builder()
                .tcp("127.0.0.1",9191)
                .route("graphql")
                .build();
    }
}
