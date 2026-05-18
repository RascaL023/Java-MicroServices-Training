package com.rascal.auth_service.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rascal.auth_service.dto.request.LoginRequest;
import com.rascal.auth_service.service.AuthService;
import com.rascal.my_lib.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auths")
public class AuthController {

    @Autowired private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @Valid @RequestBody LoginRequest request
    ) {
        return ApiResponse.success(
            HttpStatus.OK, 
            authService.login(request)
        );
    }

    @GetMapping("/test")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> test() {
        return ApiResponse.success(
            HttpStatus.OK, 
            "Test Successful!"
        );
    }
}
