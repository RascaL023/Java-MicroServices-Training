package com.rascal.auth_service.dto.mapper;

import com.rascal.auth_service.dto.request.UserRequest;
import com.rascal.auth_service.dto.response.UserResponse;
import com.rascal.auth_service.entity.Role;
import com.rascal.auth_service.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user) {
        UserResponse response = new UserResponse(
            user.getId(),
            user.getUsername(), 
            user.getEmail(), 
            user.getIsActive(), 
            user.getRoles().stream()
                .map(Role::getRole).toList()
        );

        return response;
    }

    public static User toEntity(UserRequest request) {
        User user = new User();
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(request.password());

        return user;
    }
    
}
