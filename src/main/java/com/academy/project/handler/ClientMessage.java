package com.academy.project.handler;

public class ClientMessage {

    private int statusCode;
    private String message;

    public ClientMessage(int statusCode, String message) {
        super();
        this.statusCode = statusCode;
        this.message = message;
    }
}
