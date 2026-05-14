package com.rascal.auth_service.dto.mapper;

import com.rascal.auth_service.dto.request.RoleRequest;
import com.rascal.auth_service.dto.response.RoleResponse;
import com.rascal.auth_service.entity.Role;

public class RoleMapper {

    public static Role toEntity(RoleRequest request) {
        Role role = new Role();
        role.setRole(request.getRole());
        
        return role;
    }

    public static RoleResponse toResponse(Role role) {
        RoleResponse response = new RoleResponse();

        response.setId(role.getId());
        response.setRole(role.getRole());
        response.setCreatedAt(role.getCreatedAt());
        response.setUpdatedAt(role.getUpdatedAt());
        response.setDeletedAt(role.getDeletedAt());

        return response;
    }
    
}
