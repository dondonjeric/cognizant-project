package com.academy.project.enums;

public enum Message {

    SUCCESS(""),
    CLIENT_FAULT(""),
    SYSTEM_ERROR("SYSTEM ERROR: 500 STATUS");
    private String message;
    Message(final String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
