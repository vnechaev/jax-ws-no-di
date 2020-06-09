package com.example.db.balance;

import com.example.model.request.RequestUser;
import com.example.model.response.IResponse;

public interface IBalanceDB {
    IResponse getBalance(RequestUser request) throws Exception;

}
