package com.asmith.booking.controllers.response;

/**
 * @author Alan
 */
public class TextResponse {

    private String message;

    public TextResponse() {

    }

    public TextResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
