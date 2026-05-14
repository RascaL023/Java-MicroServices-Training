package com.rascal.book_service.exception;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.rascal.book_service.util.MessageResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(exception = NotFoundException.class)
    public ResponseEntity<?> handleNotFound(
        NotFoundException e
    ) {
        return MessageResponse.error(
            "Not Found", 
            HttpStatus.NOT_FOUND, 
            404, e.getMessage()
        );
    }

    @ExceptionHandler(exception = BadRequestException.class)
    public ResponseEntity<?> handleBadRequest(
        BadRequestException e
    ) {
        return MessageResponse.error(
            "Bad Request", 
            HttpStatus.BAD_REQUEST, 
            401, e.getMessage()
        );
    }

    

    @ExceptionHandler(exception = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidation(
        MethodArgumentNotValidException e
    ) {
        var errors = e.getBindingResult().getFieldErrors().stream()
            .map(field -> Map.of(
                "field", field.getField(),
                "message", field.getDefaultMessage()
            )).toList();

        return ResponseEntity.badRequest().body(
            Map.of(
                "status", 400,
                "error", HttpStatus.BAD_REQUEST,
                "message", "Validation failed",
                "errors", errors
            )
        );
    }

    @ExceptionHandler(exception = Exception.class)
    public ResponseEntity<?> handleOther(Exception e) {
        return MessageResponse.error(
            "Internal Server Error", 
            HttpStatus.INTERNAL_SERVER_ERROR, 
            500, e.getMessage()
        );
    }
    
}
