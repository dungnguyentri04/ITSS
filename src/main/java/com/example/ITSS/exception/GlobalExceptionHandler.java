package com.example.ITSS.exception;

import com.example.ITSS.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResponse<Object>> handleResourceNotFound(NotFoundException ex, HttpServletRequest request) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(
                "not_found",
                ex.getMessage(),
                null,
                Map.of(
                        "timestamp", Instant.now(),
                        "path", request.getRequestURI()
                )
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(apiResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleOtherExceptions(Exception ex,  HttpServletRequest request) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(
                "error",
                ex.getMessage(),
                null,
                Map.of(
                        "timestamp", Instant.now(),
                        "path", request.getRequestURI()
                )
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }
}
