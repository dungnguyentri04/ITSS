package com.example.ITSS.exception;

import com.example.ITSS.dto.ApiResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ApiResponse<Object>> handleBadRequestException(BadRequestException ex,  HttpServletRequest request) {
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

    @ExceptionHandler(NullInformationException.class)
    public ResponseEntity<ApiResponse<Object>> handleNullInformationException(NullInformationException ex,  HttpServletRequest request) {
        ApiResponse<Object> apiResponse = new ApiResponse<>(
                "error",
                ex.getMessage(),
                null,
                Map.of(
                        "timestamp", Instant.now(),
                        "path", request.getRequestURI()
                )
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(apiResponse);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<String>> handleMetaDataAccessException(MethodArgumentNotValidException ex,  HttpServletRequest request) {
        String errorMessage = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", ")); // Gộp thành 1 chuỗi

        ApiResponse<String> apiResponse = new ApiResponse<>(
                "error",
                errorMessage,
                null,
                Map.of(
                        "timestamp", Instant.now(),
                        "path", request.getRequestURI()
                )
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(apiResponse);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<String>> handleIllegalArgumentException(IllegalArgumentException ex,  HttpServletRequest request) {
        ApiResponse<String> apiResponse = new ApiResponse<>(
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

    @ExceptionHandler(DontHavePermissionException.class)
    public ResponseEntity<ApiResponse<Object>> handleDontHavePermission(DontHavePermissionException ex, HttpServletRequest request) {
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
