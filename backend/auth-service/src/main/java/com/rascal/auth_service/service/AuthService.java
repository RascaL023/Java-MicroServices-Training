package com.rascal.auth_service.service;

import org.springframework.stereotype.Service;

import com.rascal.auth_service.dto.request.LoginRequest;
import com.rascal.auth_service.dto.response.LoginResponse;

@Service
public interface AuthService {

    LoginResponse login(LoginRequest request);
    
}
