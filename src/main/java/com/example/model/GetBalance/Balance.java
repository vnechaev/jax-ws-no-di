package com.example.model.GetBalance;

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
