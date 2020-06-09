package com.example.model.request;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public static List<String> listOfNames(){
        return Arrays.stream(values())
                .map(type -> type.getName())
                .collect(Collectors.toList());

    }
}
