package com.rascal.auth_service.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rascal.auth_service.dto.request.RoleRequest;
import com.rascal.auth_service.entity.Role;

@Service
public interface RoleService {

    List<Role> getRoleByIds(List<Long> ids);
    Role getById(Long id);
    Role insert(RoleRequest request);
    Page<Role> getAll(Pageable pageable);
    
} 
