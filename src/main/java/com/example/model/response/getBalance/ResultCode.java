package com.example.model.response.getBalance;

public enum ResultCode {
    SUCCESS("0"),
    TECHNICAL_ERROR("2"),
    USER_NOT_EXISTS("3"),
    PASSWORD_INCORRECT("4");

    private String code;

    ResultCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
