package com.rascal.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rascal.auth_service.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {


    
}


