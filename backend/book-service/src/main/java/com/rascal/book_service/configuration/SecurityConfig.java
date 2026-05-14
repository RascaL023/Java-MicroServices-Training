package com.rascal.book_service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rascal.book_service.service.JwtAuthFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired private JwtAuthFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity httpSecurity
    ) throws Exception {
        httpSecurity
            .csrf(c -> c.disable())
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            // ).addFilterBefore(
            //     jwtAuthFilter, 
            //     UsernamePasswordAuthenticationFilter.class
            );

        return httpSecurity.build();
    }
    
}
