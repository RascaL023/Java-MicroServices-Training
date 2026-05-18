package com.rascal.auth_service.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.rascal.auth_service.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByUsernameOrEmail(String username, String email);
    boolean existsByUsername(String username);
    @Query("""
        SELECT u FROM User u
        JOIN FETCH u.roles r
        JOIN FETCH r.permissions
        WHERE u.username = :identifier OR u.email = :identifier
    """)
    Optional<User> findByIdentifierWithRolesAndPermissions(String identifier);
}
