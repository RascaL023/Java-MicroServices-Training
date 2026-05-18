package com.rascal.auth_service.dto.response;

import java.time.LocalDateTime;

public record RegisterResponse(
    Long id,
    String username,
    String email,
    LocalDateTime createdAt
) { }
