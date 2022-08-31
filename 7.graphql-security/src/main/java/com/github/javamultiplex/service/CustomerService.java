package com.github.javamultiplex.service;

import com.github.javamultiplex.dto.Customer;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Rohit Agarwal on 31/08/22 3:54 pm
 * @copyright github.com/javamultiplex
 */
@Service
public class CustomerService {

    public final Map<Integer, Customer> customers = new ConcurrentHashMap<>();

    public final AtomicInteger customerId = new AtomicInteger();

    @Secured("ROLE_USER")
    public Mono<Customer> customerById(Integer id) {
        var customer = customers.get(id);
        return Mono.just(customer);
    }

    @PreAuthorize("hasRole('ADMIN')")
    public Mono<Customer> insert(String name) {
        final int id = customerId.incrementAndGet();
        var customer = new Customer(id, name);
        customers.put(id, customer);
        return Mono.just(customer);
    }
}
