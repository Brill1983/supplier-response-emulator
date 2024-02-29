package com.example.supplierresponseemulator.api_cloud.utils;

public class ErrorResponse {
    private final String error;

    private String message;

    public ErrorResponse(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}