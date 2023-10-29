package com.zavyalov.kiselev.coursework.common.exception;

import com.zavyalov.kiselev.coursework.common.exception.EntityNotFoundException;
import com.zavyalov.kiselev.coursework.common.exception.PermissionNotFoundException;
import com.zavyalov.kiselev.coursework.common.exception.PostNotFoundException;
import com.zavyalov.kiselev.coursework.common.exception.RoleNotFoundException;
import com.zavyalov.kiselev.coursework.common.exception.UserNotFoundException;
import com.zavyalov.kiselev.coursework.common.AppError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppError> catchException(Exception e) {
        AppError errorResponse =
                new AppError("Internal server error. Exception: " + e.getClass().toString() +
                        ". Message: " + e.getMessage());
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<AppError> handleEntityNotFoundException(EntityNotFoundException e) {
        AppError errorResponse =
                new AppError(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(PermissionNotFoundException.class)
    public ResponseEntity<AppError> handlePermissionNotFoundException(PermissionNotFoundException e) {
        AppError errorResponse =
                new AppError(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<AppError> handlePostNotFoundException(PostNotFoundException e) {
        AppError errorResponse =
                new AppError(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<AppError> handleUsernameNotFoundException(UsernameNotFoundException e) {
        AppError errorResponse =
                new AppError(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<AppError> handleRoleNotFoundException(UsernameNotFoundException e) {
        AppError errorResponse =
                new AppError(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<AppError> handleUserNotFoundException(UserNotFoundException e) {
        AppError errorResponse =
                new AppError(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AppError> handleAccessDeniedException(AccessDeniedException e) {
        AppError errorResponse =
                new AppError(e.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }
}
