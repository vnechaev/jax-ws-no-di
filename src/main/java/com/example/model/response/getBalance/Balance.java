package com.example.model.response.getBalance;

import java.math.BigDecimal;

public class Balance {
    private BigDecimal balance;

    public Balance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
