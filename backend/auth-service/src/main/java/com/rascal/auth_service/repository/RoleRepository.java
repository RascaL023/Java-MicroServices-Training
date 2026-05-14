package com.rascal.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rascal.auth_service.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRole(String role);
    
}

