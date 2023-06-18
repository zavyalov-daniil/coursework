package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.exception.PostNotFoundException;
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
        return new ResponseEntity<>(
                new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error " + e.getMessage() + e.getClass().toString()), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<AppError> handlePostNotFoundException(PostNotFoundException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Post not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<AppError> handlePostNotFoundException(UsernameNotFoundException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Username not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<AppError> handlePostNotFoundException(AccessDeniedException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.FORBIDDEN.value(), "Access denied"), HttpStatus.FORBIDDEN);
    }
}
