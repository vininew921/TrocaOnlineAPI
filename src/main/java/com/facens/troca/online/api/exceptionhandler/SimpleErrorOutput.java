package com.facens.troca.online.api.exceptionhandler;

public class SimpleErrorOutput {
    private String userMessage;
    private String devpMessage;

    public SimpleErrorOutput() {}
    public SimpleErrorOutput(String userMessage, String devpMessage) {
        this.userMessage = userMessage;
        this.devpMessage = devpMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }

    public String getDevpMessage() {
        return devpMessage;
    }

    public void setDevpMessage(String devpMessage) {
        this.devpMessage = devpMessage;
    }

}