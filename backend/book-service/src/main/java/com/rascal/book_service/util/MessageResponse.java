package com.rascal.book_service.util;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MessageResponse {

    public static ResponseEntity<Map<String, Object>> error (
        String errorType,
        HttpStatus httpStatus,
        int status,
        String msg
    ) {
        return ResponseEntity.status(status).body(
            Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status,
                "error", errorType,
                "message", msg
            )
        );
    }

    public static ResponseEntity<?> success2xx (
        HttpStatus statusCode,
        Object data
    ) {
        return ResponseEntity.status(statusCode)
            .body(Map.of("data", data));
    }

    public static ResponseEntity<?> success2xxPaged(
        HttpStatus statusCode,
        Page<?> page
    ) {
        Map<String, Object> meta = Map.of(
            "page", page.getNumber(),
            "size", page.getSize(),
            "totalElements", page.getTotalElements(),
            "totalPages", page.getTotalPages(),
            "hasNext", page.hasNext(),
            "hasPrevious", page.hasPrevious()
        );

        return ResponseEntity.status(statusCode).body(
            Map.of(
                "data", page.getContent(),
                "meta", meta
            )
        );
    }
    
}
