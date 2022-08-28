package com.github.javamultiplex.controllers;

import com.github.javamultiplex.dtos.Greeting;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.time.Instant;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author Rohit Agarwal on 27/08/22 3:27 pm
 * @copyright github.com/javamultiplex
 */
@Controller
public class GreetingsController {

    @QueryMapping
    public Greeting greeting() {
        return new Greeting("Hello, World!");
    }

    @SubscriptionMapping
    public Flux<Greeting> greetings(){
        return Flux.fromStream(Stream.generate(() -> new Greeting("Hello, World @"+ Instant.now())))
                .delayElements(Duration.ofSeconds(1))
                .take(10);
    }
}
