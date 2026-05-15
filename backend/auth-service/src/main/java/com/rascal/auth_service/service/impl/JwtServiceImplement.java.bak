package com.rascal.auth_service.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.rascal.auth_service.service.JwtService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImplement implements JwtService {

    // TODO: Make more data for securing request - response user
    private final String secKey = "w9hf20HADJIOasd9283hads9g2yeh29d82uasdh9asdh9";

    public String generateToken(
        String identifier,
        Set<String> roles,
        Set<String> permissions
    ) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("roles", roles);
        claims.put("authorities", permissions);

        return Jwts.builder()
            .header().add("kid", "my-key").and()
            .claims(claims)
            .subject(identifier)
            .issuedAt(new Date())
            .expiration(
                new Date(
                    System.currentTimeMillis() + 1000 * 60 * 15
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
        return extractAllClaims(token).getExpiration().after(new Date());
    }


    private SecretKey getSignKey() {
        byte[] keyBytes = secKey.getBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }
    
}
