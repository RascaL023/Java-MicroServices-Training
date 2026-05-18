package com.rascal.auth_service.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rascal.auth_service.configuration.AuthModeConfig;
import com.rascal.auth_service.dto.request.LoginRequest;
import com.rascal.auth_service.dto.response.LoginResponse;
import com.rascal.auth_service.entity.Permission;
import com.rascal.auth_service.entity.Role;
import com.rascal.auth_service.entity.User;
import com.rascal.auth_service.repository.UserRepository;
import com.rascal.auth_service.service.AuthService;
import com.rascal.auth_service.service.SessionService;
import com.rascal.my_lib.exception.BadRequestException;
import com.rascal.my_lib.exception.NotFoundException;

import id.rascal.filter.service.JwtService;

@Service
public class AuthServiceImplement implements AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;
    @Autowired private SessionService sessionService;
    @Autowired private AuthModeConfig authModeConfig;

    @Override
    public LoginResponse login(LoginRequest request) {
        User user = userRepository
            .findByIdentifierWithRolesAndPermissions(
                request.username()
        ).orElseThrow(() -> new NotFoundException("User tidak ditemukkan"));

        if (!passwordEncoder.matches(request.password(), user.getPassword()))
            throw new BadRequestException("Username atau password salah");

        Set<String> roles = user.getRoles().stream()
            .map(Role::getRole).collect(Collectors.toSet());

        Set<String> permissions = user.getRoles().stream()
            .flatMap(role -> role.getPermissions().stream())
            .map(Permission::getPermission)
            .collect(Collectors.toSet());


        String token, tokenType;
        if (authModeConfig.isStateful()) {
            token = sessionService.createSession(
                user.getId().toString(), 
                roles, permissions
            ); tokenType = "Session";
        } else {
            token = jwtService.generateToken(
                user.getId().toString(), 
                roles, permissions
            ); tokenType = "Bearer";
        }


        return new LoginResponse(
            user.getUsername(), 
            roles, permissions,
            tokenType, token
        );
    }
    
}
