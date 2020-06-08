package com.example.model.request;

import com.example.model.Extra;
import com.example.model.User;

import java.util.HashMap;
import java.util.Map;

public class AddUserRequest implements RequestUser {
    private Request request;

    public AddUserRequest(Request request) {
        this.request = request;
    }

    @Override
    public User user() throws Exception {
        if (!request.getRequestType().equals(RequestTypes.ADD_USER)) {
            throw new Exception("Incorrect type");
        } else {
            Map<String, String> map = new HashMap<>();
            for (Extra extra : request.getExtraList()) {
                map.put(extra.getName(), extra.getValue());
            }
            return new User(map.get("login"), map.get("password"));
        }
    }
}
