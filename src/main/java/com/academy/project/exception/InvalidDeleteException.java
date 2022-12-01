package com.academy.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidDeleteException extends RuntimeException{
    public InvalidDeleteException(String message) {
        super(message);
    }
}
