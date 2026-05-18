package com.rascal.auth_service.dto.response;

import java.time.LocalDateTime;

public record RoleResponse(
    Long id,
    String role,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    LocalDateTime deletedAt
) { }
