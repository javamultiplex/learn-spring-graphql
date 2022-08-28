package com.github.javamultiplex;

import com.github.javamultiplex.dtos.Customer;
import com.github.javamultiplex.dtos.Greeting;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.graphql.client.RSocketGraphQlClient;

@SpringBootApplication
public class GraphqlClientsApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlClientsApplication.class, args);
    }

    @Bean
    public ApplicationRunner applicationRunner(HttpGraphQlClient http, RSocketGraphQlClient rSocket) {
        return new ApplicationRunner() {
            @Override
            public void run(ApplicationArguments args) throws Exception {
                var httpRequestDocument = """
                        						query {
                        							customers{
                        								id,
                        								name
                        							}
                        						}
                        """;
                http
                        .document(httpRequestDocument)
                        .retrieve("customers")
                        .toEntityList(Customer.class)
                        .subscribe(System.out::println);


                var rSocketRequestDocument = """
                        subscription {
                            greetings {
                                greeting
                            }
                        }
                        """;

                rSocket
                        .document(rSocketRequestDocument)
                        .retrieveSubscription("greetings")
                        .toEntity(Greeting.class)
                        .subscribe(System.out::println);

            }
        };
    }
}
