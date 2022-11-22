package com.academy.project.handler;

import com.academy.project.exception.InvalidStringFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ClientMessage> invalidStringFormatException(InvalidStringFormatException ex){
        return new ResponseEntity<>(new ClientMessage(HttpStatus.BAD_REQUEST.value(), ex.getMessage()), HttpStatus.BAD_REQUEST);
    }


}
