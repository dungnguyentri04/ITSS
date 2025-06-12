package com.example.ITSS.exception;

public class DontHavePermissionException extends RuntimeException {
    public DontHavePermissionException(String message) {
        super(message);
    }
}
