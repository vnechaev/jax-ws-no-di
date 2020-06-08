package com.example.db;

import com.example.model.User;
import com.example.model.request.RequestUser;

import java.math.BigDecimal;

public interface IUserDb {
    boolean addUser(RequestUser user) throws Exception;
    BigDecimal getBalance(User user);
}
