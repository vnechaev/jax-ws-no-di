package com.example.model.response.getBalance;

public enum ResultCode {
    SUCCESS(0),
    TECHNICAL_ERROR(2),
    USER_NOT_EXISTS(3),
    PASSWORD_INCORRECT(4);

    private int code;

    ResultCode(int code) {
        this.code = code;
    }
}
