package com.rascal.auth_service.dto.mapper;

import com.rascal.auth_service.dto.request.UserRequest;
import com.rascal.auth_service.dto.response.UserResponse;
import com.rascal.auth_service.entity.Role;
import com.rascal.auth_service.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse();
        response.setUsername(user.getUsername());
        response.setEmail(user.getEmail());
        response.setIsActive(user.getIsActive());
        response.setRoles(user.getRoles().stream()
            .map(Role::getRole).toList()
        );

        return response;
    }

    public static User toEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());

        return user;
    }
    
}
