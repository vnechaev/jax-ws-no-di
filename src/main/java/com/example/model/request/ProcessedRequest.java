package com.example.model.request;

import com.example.model.Extra;
import com.example.model.User;

import java.util.HashMap;
import java.util.Map;

public class ProcessedRequest implements RequestUser {
    private RequestXml requestXml;

    public ProcessedRequest(RequestXml requestXml) {
        this.requestXml = requestXml;
    }

    @Override
    public User user() throws Exception {
        Map<String, String> map = new HashMap<>();
        for (Extra extra : requestXml.getExtraList()) {
            map.put(extra.getName(), extra.getValue());
        }
        return new User(map.get("login"), map.get("password"));//TODO map.get() is bad
    }
}
