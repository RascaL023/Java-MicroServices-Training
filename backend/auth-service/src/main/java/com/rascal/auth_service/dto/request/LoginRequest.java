package com.rascal.auth_service.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "Username tidak boleh kosong")
    String username,
    
    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 8, message = "Password tidak valid")
    String password
) { }
