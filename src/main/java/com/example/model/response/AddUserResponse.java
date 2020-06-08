package com.example.model.response;

public class AddUserResponse implements IResponse {
    private ResponseXml responseXml;

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
