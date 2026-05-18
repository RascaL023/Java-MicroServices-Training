package com.rascal.auth_service.service;

import java.util.Set;

public interface SessionService {

    String createSession(
        String identifier,
        Set<String> roles,
        Set<String> authorities
    );

    void deleteSession(String sessionToken);
    
}
