package com.example.model.request;

import com.example.model.Extra;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddUserRequestTest {

    @Test
    void test() throws Exception {
        Request request = new Request();
        request.setRequestType("CREATE-AGT");
        String bob = "Bob";
        String pass = "123";
        request.setExtraList(Arrays.asList(new Extra("login", bob), new Extra("password", pass)));

        AddUserRequest addUserRequest = new AddUserRequest(request);
        assertEquals(addUserRequest.user().getLogin(), bob);
        assertEquals(addUserRequest.user().getPassword(), pass);

    }
}