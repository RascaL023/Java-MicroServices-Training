package com.rascal.auth_service.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rascal.auth_service.dto.request.LoginRequest;
import com.rascal.auth_service.dto.request.RegisterRequest;
import com.rascal.auth_service.dto.request.UserRequest;
import com.rascal.auth_service.dto.response.RegisterResponse;
import com.rascal.auth_service.entity.User;
import com.rascal.auth_service.service.AuthService;
import com.rascal.auth_service.service.UserService;
import com.rascal.my_lib.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auths")
// @CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true")
public class AuthController {

    @Autowired private AuthService authService;
    @Autowired private UserService userService;
    private final static long USER_ROLE_ID = 3L;

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @Valid @RequestBody LoginRequest request
    ) {
        return ApiResponse.success(
            HttpStatus.OK, 
            authService.login(request)
        );
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
        @Valid @RequestBody RegisterRequest request
    ) {
        UserRequest userRequest = new UserRequest(
            request.username(), 
            request.email(), 
            request.password(), 
            List.of(USER_ROLE_ID)
        );

        User user = userService.insertUser(userRequest);
        return ApiResponse.success(
            HttpStatus.CREATED, 
            new RegisterResponse(
                user.getId(), 
                user.getUsername(), 
                user.getEmail(), 
                user.getCreatedAt()
            )
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
