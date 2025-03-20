package com.devteria.indentity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed");

    private final int code;
    private final String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}