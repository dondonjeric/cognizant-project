package com.academy.project.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class NumberFormatException extends Exception {
    public NumberFormatException(String message) {
        super(message);
    }
}
