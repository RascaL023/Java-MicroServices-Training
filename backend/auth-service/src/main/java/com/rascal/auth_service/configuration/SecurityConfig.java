package com.rascal.auth_service.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rascal.my_lib.exception.SecurityExceptionHandler;

import id.rascal.filter.HeaderAuthFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired private HeaderAuthFilter headerAuthFilter;
    @Autowired private SecurityExceptionHandler securityExceptionHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity httpSecurity
    ) throws Exception {
        return httpSecurity
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                    "/api/auths/login",
                    "/api/auths/register"
                ).permitAll()
                .anyRequest().authenticated()
            ).exceptionHandling(ex -> ex
                .authenticationEntryPoint(securityExceptionHandler)
                .accessDeniedHandler(securityExceptionHandler)
            ).addFilterBefore(
                headerAuthFilter, 
                UsernamePasswordAuthenticationFilter.class
            ).build();
    }
    
}
