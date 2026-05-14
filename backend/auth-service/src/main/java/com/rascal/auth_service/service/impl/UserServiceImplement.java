package com.rascal.auth_service.service.impl;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.rascal.auth_service.dto.mapper.UserMapper;
import com.rascal.auth_service.dto.request.UserRequest;
import com.rascal.auth_service.entity.User;
import com.rascal.auth_service.repository.UserRepository;
import com.rascal.auth_service.service.RoleService;
import com.rascal.auth_service.service.UserService;
import com.rascal.my_lib.exception.NotFoundException;

@Service
public class UserServiceImplement implements UserService {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleService roleService;
    @Autowired private PasswordEncoder passwordEncoder;

    public User insertUser(UserRequest request) {
        User user = UserMapper.toEntity(request);
        user.setIsActive(true);
        user.setCreatedAt(LocalDateTime.now());
        user.setRoles(
            roleService.getRoleByIds(request.getRoleIds())
                .stream().collect(Collectors.toSet())
        );
        user.setPassword(passwordEncoder.encode(
            user.getPassword())
        );

        return userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("User tidak ditemukkan"));
    }

    public Page<User> getAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    
}
