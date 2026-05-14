package com.rascal.auth_service;

import java.time.LocalDateTime;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.rascal.auth_service.entity.Role;
import com.rascal.auth_service.entity.User;
import com.rascal.auth_service.repository.RoleRepository;
import com.rascal.auth_service.repository.UserRepository;

@Component
public class AdminInitializer implements CommandLineRunner {

    @Autowired private PasswordEncoder passwordEncoder;
    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;

    @Value("${admin.username}") private String adminUsername;
    @Value("${admin.password}") private String adminPassword;
    @Value("${admin.email}") private String adminEmail;

    @Override
    public void run(String ...args) {
        if (userRepository.existsByUsername(adminUsername)) return;

        Role adminRole = new Role();
        adminRole.setRole("ADMIN");
        adminRole.setCreatedAt(LocalDateTime.now());
        adminRole = roleRepository.save(adminRole);

        User admin = new User();
        admin.setUsername(adminUsername);
        admin.setPassword(passwordEncoder.encode(adminPassword));
        admin.setEmail(adminEmail);
        admin.setRoles(Set.of(adminRole));
        admin.setCreatedAt(adminRole.getCreatedAt());
        admin.setIsActive(true);

        userRepository.save(admin);
        System.out.println("Admin created!!!");
    }
}
