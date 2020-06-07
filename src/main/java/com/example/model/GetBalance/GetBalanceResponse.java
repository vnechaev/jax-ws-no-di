package com.example.model.GetBalance;

public class GetBalanceResponse {
    private float balance;
    private String resultCode;

    public GetBalanceResponse(float balance, String resultCode) {
        this.balance = balance;
        this.resultCode = resultCode;
    }
}
