package com.example.model.addUser;

public enum ResultCode {
    SUCCESS(0),
    LOGIN_EXIST(1),
    TECHNICAL_ERROR(2);

    private int code;

    ResultCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
