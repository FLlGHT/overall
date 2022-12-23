package com.flight.overall.dto;

import org.apache.http.HttpStatus;

public class ResponseEntity {

    String message;

    public ResponseEntity(String message) {
        this.message = message;
    }

    public ResponseEntity() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
