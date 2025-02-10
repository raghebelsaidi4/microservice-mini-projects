package com.ragheb.registration.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

@RestControllerAdvice
public class UserRegistrationServiceHandler {

    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<String> sQLIntegrityConstraintViolationException(SQLIntegrityConstraintViolationException sqlException) {
        return new ResponseEntity<>(sqlException.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
