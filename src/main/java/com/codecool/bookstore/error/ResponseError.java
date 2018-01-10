package com.codecool.bookstore.error;

public class ResponseError {

    private String errorCode;
    private String errorMessage;

    public ResponseError(String errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
