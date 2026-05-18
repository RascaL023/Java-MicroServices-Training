package com.rascal.auth_service.dto.response;

import java.util.Set;

public record LoginResponse(
    String username,
    Set<String> roles,
    Set<String> permissions,
    String tokenType,
    String accessToken
) { }
