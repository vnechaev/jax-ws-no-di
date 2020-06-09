package com.example.model.response.addUser;

public enum ResultCode {
    SUCCESS("0"),
    LOGIN_EXIST("1"),
    TECHNICAL_ERROR("2");

    private String code;

    ResultCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
