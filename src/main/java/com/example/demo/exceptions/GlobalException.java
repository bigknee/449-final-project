package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> handleRuntimeException(RuntimeException ex) {
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String message = ex.getMessage();

        if (message.equals("Email already in use")) {
            status = HttpStatus.CONFLICT; // 409
        } else if (message.equals("Invalid credentials")) {
            status = HttpStatus.UNAUTHORIZED; // 401
        } else if (message.equals("Recipe not found")) {
            status = HttpStatus.NOT_FOUND; // 404
        } else if (message.equals("Forbidden")) {
            status = HttpStatus.FORBIDDEN; // 403
        }

        return ResponseEntity.status(status).body(Map.of(
                "status", status.value(),
                "message", message,
                "timestamp", LocalDateTime.now().toString()
        ));
    }
}