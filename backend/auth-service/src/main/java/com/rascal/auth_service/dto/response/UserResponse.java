package com.rascal.auth_service.dto.response;

import java.util.List;

public record UserResponse(
    String username,
    String email,
    Boolean isActive,
    List<String> roles
) { }
