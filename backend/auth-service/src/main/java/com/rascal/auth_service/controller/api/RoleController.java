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

import com.rascal.auth_service.dto.mapper.RoleMapper;
import com.rascal.auth_service.dto.request.RoleRequest;
import com.rascal.auth_service.service.RoleService;
import com.rascal.my_lib.util.ApiResponse;

@RestController
@RequestMapping("/api/roles")
public class RoleController {

    @Autowired private RoleService roleService;

    @GetMapping
    // @PreAuthorize("hasAuthority('role.readAll')")
    public ResponseEntity<?> getAll(Pageable pageable) {
        return ApiResponse.paged(
            HttpStatus.OK,
            roleService.getAll(pageable)
                .map(RoleMapper::toResponse)
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRoleById(@PathVariable Long id) {
        return ApiResponse.success(
            HttpStatus.OK, 
            RoleMapper.toResponse(roleService.getById(id))
        );
    }

    @PostMapping
    public ResponseEntity<?> createRole(
        @RequestBody RoleRequest request
    ) {
        return ApiResponse.success(
            HttpStatus.CREATED, 
            RoleMapper.toResponse(roleService.insert(request))
        );
    }
    
}
