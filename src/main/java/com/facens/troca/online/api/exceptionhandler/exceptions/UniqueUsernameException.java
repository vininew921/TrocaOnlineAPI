package com.facens.troca.online.api.exceptionhandler.exceptions;

public class UniqueUsernameException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private String message;

    public UniqueUsernameException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}