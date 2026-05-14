package com.rascal.auth_service.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserResponse {

    private String username;
    private String email;
    private Boolean isActive;
    private List<String> roles;
    
}
