package com.rascal.auth_service.dto.mapper;

import com.rascal.auth_service.dto.request.RoleRequest;
import com.rascal.auth_service.dto.response.RoleResponse;
import com.rascal.auth_service.entity.Role;

public class RoleMapper {

    public static Role toEntity(RoleRequest request) {
        Role role = new Role();
        role.setRole(request.role());
        
        return role;
    }

    public static RoleResponse toResponse(Role role) {
        RoleResponse response = new RoleResponse(
            role.getId(), 
            role.getRole(), 
            role.getCreatedAt(), 
            role.getUpdatedAt(), 
            role.getDeletedAt()
        );

        return response;
    }
    
}
