package com.rascal.auth_service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.rascal.auth_service.dto.request.UserRequest;
import com.rascal.auth_service.entity.User;

public interface UserService  {

    User insertUser(UserRequest req);
    User getUserById(Long id);
    Page<User> getAll(Pageable pageable);
    
}

