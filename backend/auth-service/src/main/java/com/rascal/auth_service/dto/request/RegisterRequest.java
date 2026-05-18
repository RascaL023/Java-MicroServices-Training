package com.rascal.auth_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "Username wajib diisi")
    @Size(
        min = 5, max = 10, 
        message = "Username minimal 5 dan maksimal 10 karakter"
    ) String username,

    @NotBlank(message = "Email wajib diisi")
    @Size(
        min = 10,
        message = "Email minimal 10"
    ) String email,

    @NotBlank(message = "Password wajib diisi")
    @Size(
        min = 8,
        message = "Password minimal 8 karakter"
    ) String password
) { }
