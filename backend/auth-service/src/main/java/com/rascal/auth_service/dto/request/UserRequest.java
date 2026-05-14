package com.rascal.auth_service.dto.request;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserRequest {

    @NotBlank(message = "Username tidak boleh kosong")
    @Size(min = 5, message = "Username terlalu pendek")
    private String username;
    
    @NotBlank(message = "Email tidak boleh kosong")
    @Size(min = 14, message = "Email terlalu pendek")
    private String email;
    
    @NotBlank(message = "Password tidak boleh kosong")
    @Size(min = 8, message = "Password terlalu pendek")
    private String password;

    @NotEmpty(message = "Role minimal 1")
    private List<Long> roleIds;
    
}
