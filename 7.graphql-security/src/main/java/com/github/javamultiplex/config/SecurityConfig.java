package com.github.javamultiplex.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Rohit Agarwal on 31/08/22 4:00 pm
 * @copyright github.com/javamultiplex
 */
@Configuration
@EnableReactiveMethodSecurity
public class SecurityConfig {


    /**
     * How to disable Spring Security Programmatically
     *
     * @return
     */
//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(){
//        return new SecurityWebFilterChain() {
//            @Override
//            public Mono<Boolean> matches(ServerWebExchange exchange) {
//                return Mono.just(false);
//            }
//
//            @Override
//            public Flux<WebFilter> getWebFilters() {
//                return Flux.empty();
//            }
//        };
//    }
    @Bean
    public MapReactiveUserDetailsService mapReactiveUserDetailsService() {

        final List<UserDetails> userDetails = Map.of("Rohit", new String[]{"USER"},
                        "Bhavan", "ADMIN,USER".split(","))
                .entrySet()
                .stream()
                .map(e -> User.builder()
                        .username(e.getKey())
                        .password(passwordEncoder().encode("pw"))
                        .roles(e.getValue())
                        .build())
                .collect(Collectors.toList());
        return new MapReactiveUserDetailsService(userDetails);
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(c -> c.anyExchange().permitAll())
                .httpBasic(Customizer.withDefaults())
                .build();

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
