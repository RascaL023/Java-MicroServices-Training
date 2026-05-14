package com.rascal.auth_service.service.impl;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rascal.auth_service.dto.request.LoginRequest;
import com.rascal.auth_service.dto.response.LoginResponse;
import com.rascal.auth_service.entity.Permission;
import com.rascal.auth_service.entity.Role;
import com.rascal.auth_service.entity.User;
import com.rascal.auth_service.repository.UserRepository;
import com.rascal.auth_service.service.AuthService;
import com.rascal.auth_service.service.JwtService;
import com.rascal.my_lib.exception.BadRequestException;
import com.rascal.my_lib.exception.NotFoundException;

@Service
public class AuthServiceImplement implements AuthService {

    @Autowired private UserRepository userRepository;
    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        User user = userRepository
            .findByIdentifierWithRolesAndPermissions(
                request.getUsername()
        ).orElseThrow(() -> new NotFoundException("User tidak ditemukkan"));

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword()))
            throw new BadRequestException("Username atau password salah");

        Set<String> roles = user.getRoles().stream()
            .map(Role::getRole).collect(Collectors.toSet());

        Set<String> permissions = user.getRoles().stream()
            .flatMap(role -> role.getPermissions().stream())
            .map(Permission::getPermission)
            .collect(Collectors.toSet());

        String token = jwtService.generateToken(request.getUsername(), roles, permissions);

        return LoginResponse.builder()
            .accessToken(token)
            .tokenType("Bearer")
            .username(user.getUsername())
            .roles(roles)
            .permissions(permissions)
            .build();
    }
    
}
