package com.example.model.response.getBalance;

import com.example.model.Extra;
import com.example.model.response.IResponse;
import com.example.model.response.ResponseXml;

import java.math.BigDecimal;

public class GetBalanceResponse implements IResponse {
    private ResponseXml responseXml;

    public GetBalanceResponse(ResultCode resultCode) {
        this(resultCode, BigDecimal.ZERO);
    }

    public GetBalanceResponse(ResultCode resultCode, BigDecimal balance) {
        ResponseXml responseXml = new ResponseXml();
        responseXml.setResultCode(resultCode.getCode());
        responseXml.getExtraList().add(new Extra("balance", String.valueOf(balance)));
        this.responseXml = responseXml;
    }

    @Override
    public ResponseXml response() {
        return responseXml;
    }
}
