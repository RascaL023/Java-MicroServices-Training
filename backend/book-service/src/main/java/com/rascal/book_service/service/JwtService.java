package com.rascal.book_service.service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final String secKey = "w9hf20HADJIOasd9283hads9g2yeh29d82uasdh9asdh9";

    public String generateToken(
        Long userId,
        Set<String> roles,
        Set<String> permissions
    ) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("permissions", permissions);

        return Jwts.builder()
            .claims(claims)
            .subject(userId.toString())
            .issuedAt(new Date())
            .expiration(
                new Date(
                    System.currentTimeMillis() + 1000 * 60 * 5
                )
            ).signWith(getSignKey())
            .compact();
    }

    public Claims extractAllClaims(String token) {
        return Jwts.parser()
            .verifyWith(getSignKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
            
    }

    public boolean isValid(String token) {
        return extractAllClaims(token)
            .getExpiration().after(new Date());
    }

    private SecretKey getSignKey() {
        return Keys.hmacShaKeyFor(secKey.getBytes());
    }
    
}

