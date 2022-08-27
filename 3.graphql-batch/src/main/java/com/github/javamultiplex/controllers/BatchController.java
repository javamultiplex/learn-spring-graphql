package com.github.javamultiplex.controllers;

import com.github.javamultiplex.dtos.Account;
import com.github.javamultiplex.dtos.Customer;
import org.springframework.graphql.data.method.annotation.BatchMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rohit Agarwal on 27/08/22 1:40 pm
 * @copyright github.com/javamultiplex
 */
@Controller
public class BatchController {

    @QueryMapping
    public Collection<Customer> customers() {
        return List.of(new Customer(1, "A"), new Customer(2, "B"));
    }


    @BatchMapping(typeName = "Customer")
    public Map<Customer, Account> account(List<Customer> customers) {
        System.out.println("getting accounts for " + customers.size() + " customers.");
        return customers
                .stream()
                .collect(Collectors.toMap(customer -> customer, customer -> new Account(customer.id())));
    }

    /**
     * This code has N+1 problem
     * @param customer
     * @return
     */
//    @SchemaMapping(typeName = "Customer")
//    public Account account(Customer customer){
//        System.out.println("Getting account for Customer : "+customer);
//        return new Account(customer.id());
//    }

}
