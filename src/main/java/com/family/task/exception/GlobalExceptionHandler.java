package com.family.task.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleNotFoundException(EntityNotFoundException e){

        return
                ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(Map.of("Error", "NOT_FOUND", "message",e.getMessage()));

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e){

        Map<String,String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach((error)->{
            errors.put(error.getField(),error.getDefaultMessage());
        });

        return
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(errors);
    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationException(ConstraintViolationException e){

        return
                ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(Map.of("Error", "BAD_REQUEST", "message",e.getMessage()));
    }
}
