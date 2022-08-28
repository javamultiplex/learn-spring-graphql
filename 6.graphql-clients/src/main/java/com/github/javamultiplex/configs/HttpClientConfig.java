package com.github.javamultiplex.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;

/**
 * @author Rohit Agarwal on 28/08/22 3:24 pm
 * @copyright github.com/javamultiplex
 */
@Configuration
public class HttpClientConfig {

    @Bean
    public HttpGraphQlClient httpGraphQlClient(){
        return HttpGraphQlClient.builder()
                .url("http://127.0.0.1:8080/graphql")
                .build();
    }
}
