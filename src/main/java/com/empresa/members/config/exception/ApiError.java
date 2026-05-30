package com.empresa.members.config.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<FieldErrorDetail> errors
) {

    public record FieldErrorDetail(String field, String message) { }

    public static ApiError of(HttpStatus status, String message, String path) {
        return new ApiError(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
                message, path, null);
    }

    public static ApiError of(HttpStatus status, String message, String path,
                              List<FieldErrorDetail> errors) {
        return new ApiError(LocalDateTime.now(), status.value(), status.getReasonPhrase(),
                message, path, errors);
    }
}
