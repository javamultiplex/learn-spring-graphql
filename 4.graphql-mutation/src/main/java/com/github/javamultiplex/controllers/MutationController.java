package com.github.javamultiplex.controllers;

import com.github.javamultiplex.dtos.Customer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Rohit Agarwal on 27/08/22 2:25 pm
 * @copyright github.com/javamultiplex
 */

@Controller
public class MutationController {

    private final Map<Integer, Customer> db = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    @MutationMapping
    public Customer addCustomer(@Argument String name) {
        final int customerId = id.incrementAndGet();
        var customer = new Customer(customerId, name);
        db.put(customerId, customer);
        return customer;
    }

    @QueryMapping
    public Customer customerById(@Argument Integer id) {
        return db.get(id);
    }
}
