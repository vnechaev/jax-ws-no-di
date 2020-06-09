package com.example.db.user;

import com.example.model.User;
import com.example.model.addUser.ResultCode;
import com.example.model.request.RequestUser;
import com.example.model.response.IResponse;

import java.math.BigDecimal;

public interface IUserDb {
    ResultCode addUser(RequestUser user) throws Exception;
    IResponse getBalance(RequestUser request) throws Exception;
}
