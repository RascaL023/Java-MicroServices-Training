package com.rascal.auth_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RoleRequest(
    @NotBlank(message = "Role tidak boleh kosong")
    String role
) { }
