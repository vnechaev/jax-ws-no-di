package com.example.db.user;

import com.example.model.request.RequestUser;
import com.example.model.response.IResponse;

public interface IUserDb {
    IResponse addUser(RequestUser user) throws Exception;
}
