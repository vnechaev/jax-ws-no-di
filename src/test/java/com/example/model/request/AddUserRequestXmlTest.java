package com.example.model.request;

import com.example.model.Extra;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AddUserRequestXmlTest {

    @Test
    void test() throws Exception {
        RequestXml requestXml = new RequestXml();
        requestXml.setRequestType("CREATE-AGT");
        String bob = "Bob";
        String pass = "123";
        requestXml.setExtraList(Arrays.asList(new Extra("login", bob), new Extra("password", pass)));

        ProcessedRequest addUserRequest = new ProcessedRequest(requestXml);
        assertEquals(addUserRequest.user().getLogin(), bob);
        assertEquals(addUserRequest.user().getPassword(), pass);

    }
}