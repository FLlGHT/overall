package com.flight.overall.dto;

public class MessageResponseEntity {

    String message;

    public MessageResponseEntity(String message) {
        this.message = message;
    }

    public MessageResponseEntity() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
