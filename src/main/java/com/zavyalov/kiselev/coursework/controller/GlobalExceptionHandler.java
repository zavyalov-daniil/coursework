package com.zavyalov.kiselev.coursework.controller;

import com.zavyalov.kiselev.coursework.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<AppError> catchException(Exception e) {
        return new ResponseEntity<>(
                new AppError(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler(PostNotFoundException.class)
    public ResponseEntity<AppError> handlePostNotFoundException(PostNotFoundException e) {
        return new ResponseEntity<>(new AppError(HttpStatus.NOT_FOUND.value(), "Post not found"), HttpStatus.NOT_FOUND);
    }
}
