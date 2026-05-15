package com.rascal.auth_service.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;

@Service
public interface JwtService {

    String generateToken(
        String identifier, 
        Set<String> roles,
        Set<String> permissions
    );
    Claims extractAllClaims(String token);
    boolean isValid(String token);
    
}
