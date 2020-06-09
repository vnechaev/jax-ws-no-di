package com.example.db.balance;

import com.example.model.User;

import java.math.BigDecimal;

public interface IBalanceDB {
    BigDecimal getBalance(User user);

}
