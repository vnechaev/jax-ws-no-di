package com.example.db;

import com.example.model.User;

import java.math.BigDecimal;

public interface IBalanceDB {
    BigDecimal getBalance(User user);

}
