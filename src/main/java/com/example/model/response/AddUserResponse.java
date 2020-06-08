package com.example.model.response;

public class AddUserResponse implements IResponse {
    private Response response;

    public AddUserResponse(String resultCode) {
        Response response = new Response();
        response.setResultCode(resultCode);
        this.response = response;
    }

    @Override
    public Response response() {
        return response;
    }

    @Override
    public String toString() {
        return "AddUserResponse{" +
                "response=" + response +
                '}';
    }
}
