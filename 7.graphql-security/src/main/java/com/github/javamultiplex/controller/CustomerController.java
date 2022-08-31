package com.github.javamultiplex.controller;

import com.github.javamultiplex.dto.Customer;
import com.github.javamultiplex.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * @author Rohit Agarwal on 31/08/22 3:58 pm
 * @copyright github.com/javamultiplex
 */
@Controller
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @QueryMapping
    public Mono<Customer> customerById(@Argument Integer id) {
        return customerService.customerById(id);
    }

    @MutationMapping
    public Mono<Customer> insert(@Argument String name) {
        return customerService.insert(name);
    }
}
