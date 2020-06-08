package com.example.model.request;

public enum RequestTypes {
    ADD_USER("CREATE-AGT"),
    GET_BALANCE("GET-BALANCE");
    private String name;

    RequestTypes(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
