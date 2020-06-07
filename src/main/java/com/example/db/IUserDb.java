package com.example.db;

import com.example.model.User;

import java.math.BigDecimal;

public interface IUserDb {
    boolean addUser(User user);
    BigDecimal getBalance(User user);
}
