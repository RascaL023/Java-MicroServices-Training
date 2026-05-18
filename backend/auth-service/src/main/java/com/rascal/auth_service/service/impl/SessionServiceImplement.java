package com.rascal.auth_service.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.rascal.auth_service.service.SessionService;

import tools.jackson.databind.ObjectMapper;

@Service
public class SessionServiceImplement implements SessionService {

    @Autowired private RedisTemplate<String, String> redisTemplate;
    @Autowired private ObjectMapper objectMapper;

    @Value("${auth.session.ttl-hours:24}")
    private long ttlHours;

    private static final String sessionPrefix = "session:";

    @Override
    public String createSession(
        String identifier,
        Set<String> roles,
        Set<String> authorities
    ) {
        String sessionToken = UUID.randomUUID().toString();
        String key = sessionPrefix + sessionToken;

        Map<String, Object> sessionData = new HashMap<>();
        sessionData.put("subject", identifier);
        sessionData.put("roles", roles);
        sessionData.put("authorities", authorities);

        String json = objectMapper.writeValueAsString(sessionData);
        redisTemplate.opsForValue().set(key, json, ttlHours, TimeUnit.HOURS);

        return sessionToken;
    }

    @Override
    public void deleteSession(String sessionToken) {
        redisTemplate.delete(sessionPrefix + sessionToken);
    }

    
}
