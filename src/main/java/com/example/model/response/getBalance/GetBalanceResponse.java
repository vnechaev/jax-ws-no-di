package com.example.model.response.getBalance;

import com.example.model.Extra;
import com.example.model.response.IResponse;
import com.example.model.response.ResponseXml;

import java.math.BigDecimal;

public class GetBalanceResponse implements IResponse {
    private ResponseXml responseXml;

    public GetBalanceResponse(String resultCode) {
        this(resultCode, BigDecimal.ZERO);
    }

    public GetBalanceResponse(String resultCode, BigDecimal balance) {
        ResponseXml responseXml = new ResponseXml();
        responseXml.setResultCode(resultCode);
        responseXml.getExtraList().add(new Extra("balance", String.valueOf(balance)));
        this.responseXml = responseXml;
    }

    @Override
    public ResponseXml response() {
        return responseXml;
    }
}