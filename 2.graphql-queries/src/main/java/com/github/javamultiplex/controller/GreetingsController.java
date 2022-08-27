package com.github.javamultiplex.controller;

import com.github.javamultiplex.dto.Account;
import com.github.javamultiplex.dto.Customer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.List;

/**
 * @author Rohit Agarwal on 27/08/22 12:53 am
 * @copyright github.com/javamultiplex
 */
@Controller
public class GreetingsController {

    @QueryMapping
    public String hello() {
        return "Hello World";
    }


    @SchemaMapping(typeName = "Query", field = "helloWithName")
    public String helloWithName(@Argument String name) {
        return "Hello " + name + "!";
    }

    @QueryMapping
    public Customer customerById(@Argument int id) {
        return new Customer(id, Math.random() > 0.5 ? "A" : "B");
    }


    @QueryMapping
    public Flux<Customer> customers() {
        final List<Customer> customers = List.of(new Customer(1, "A"),
                new Customer(2, "B"));
        return Flux.fromIterable(customers);
    }

    @SchemaMapping(typeName = "Customer")
    public Mono<Account> account(Customer customer){
        return Mono.just(new Account(customer.id()));
    }
}
