package com.example.model.response.addUser;

import com.example.model.response.IResponse;
import com.example.model.response.ResponseXml;

public class AddUserResponse implements IResponse {
    private ResponseXml responseXml;

    public AddUserResponse(ResultCode resultCode){
        this(resultCode.getCode());
    }
    public AddUserResponse(String resultCode) {
        ResponseXml responseXml = new ResponseXml();
        responseXml.setResultCode(resultCode);
        this.responseXml = responseXml;
    }

    @Override
    public ResponseXml response() {
        return responseXml;
    }

    @Override
    public String toString() {
        return "AddUserResponse{" +
                "response=" + responseXml +
                '}';
    }
}
