package com.rascal.auth_service.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record UserRequest(
    @NotBlank(message = "Username tidak boleh kosong")
    @Size(min = 5, message = "Username terlalu pendek")
    String username,
    
    @NotBlank(message = "Email tidak boleh kosong")
    @Size(min = 14, message = "Email terlalu pendek")
    String email,
    
    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 8, message = "Password terlalu pendek")
    String password,

    @NotEmpty(message = "Role minimal 1")
    List<Long> roleIds
) { }
