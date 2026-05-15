package com.rascal.auth_service.controller.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rascal.auth_service.dto.mapper.UserMapper;
import com.rascal.auth_service.dto.request.UserRequest;
import com.rascal.auth_service.service.UserService;
import com.rascal.my_lib.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired private UserService userService;

    @PostMapping
    @PreAuthorize("hasAuthority('user.create')")
    public ResponseEntity<?> insertUser(
        @Valid @RequestBody UserRequest request
    ) {
        return ApiResponse.success(
            HttpStatus.CREATED, 
            UserMapper.toResponse(
                userService.insertUser(request)
            )
        );
    }

    @GetMapping
    @PreAuthorize("hasAuthority('user.readAll')")
    public ResponseEntity<?> getAllPaged(Pageable pageable) {
        return ApiResponse.paged(
            HttpStatus.OK,
            userService.getAll(pageable)
                .map(UserMapper::toResponse)
        );
    }

    @PreAuthorize("#id == authentication.principal.claims['sub'] or hasAuthority('user.readAll')")
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id) {
        return ApiResponse.success(
            HttpStatus.OK, 
            UserMapper.toResponse(userService.getUserById(Long.parseLong(id)))
        );
    }
    
}
