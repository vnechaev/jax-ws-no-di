package com.example.db;

import com.example.model.User;
import com.example.model.addUser.ResultCode;
import com.example.model.request.RequestUser;

import java.math.BigDecimal;

public interface IUserDb {
    ResultCode addUser(RequestUser user) throws Exception;
    BigDecimal getBalance(User user);
}
