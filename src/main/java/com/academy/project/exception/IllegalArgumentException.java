package com.academy.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IllegalArgumentException extends Exception {
    public IllegalArgumentException(String message) {
        super(message);
    }
}
