package com.rascal.auth_service.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.rascal.auth_service.dto.mapper.RoleMapper;
import com.rascal.auth_service.dto.request.RoleRequest;
import com.rascal.auth_service.entity.Role;
import com.rascal.auth_service.repository.RoleRepository;
import com.rascal.auth_service.service.RoleService;
import com.rascal.my_lib.exception.NotFoundException;

@Service
public class RoleServiceImplement implements RoleService {

    @Autowired private RoleRepository roleRepository;

    public List<Role> getRoleByIds(List<Long> ids) {
        List<Role> roles = roleRepository.findAllById(ids);
        if (roles.isEmpty()) 
            throw new NotFoundException("Role tidak ditemukkan");

        return roles;
    }

    public Role getById(Long id) {
        return roleRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Role tidak ditemukkan"));
    }

    public Role insert(RoleRequest request) {
        Role role = RoleMapper.toEntity(request);
        role.setCreatedAt(LocalDateTime.now());

        return roleRepository.save(role);
    }

    public Page<Role> getAll(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }
    
}
