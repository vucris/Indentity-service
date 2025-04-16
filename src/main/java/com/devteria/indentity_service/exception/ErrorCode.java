package com.devteria.indentity_service.exception;

public enum ErrorCode {
    USER_EXISTED(1001, "User existed"),
    USER_NOT_EXISTED(1002, "User not existed"),
    UNAUTHENTICATED(1006, "UNAUTHENTICATED");
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