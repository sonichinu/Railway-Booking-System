package com.irctc.exception;

public class ApiException extends RuntimeException{
    private String message;

    public ApiException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
