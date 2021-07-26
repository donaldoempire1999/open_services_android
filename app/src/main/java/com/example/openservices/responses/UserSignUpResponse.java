package com.example.openservices.responses;

import com.google.gson.annotations.SerializedName;

public class UserSignUpResponse {
    //
//    @SerializedName("response_code")
//    private int response;
    @SerializedName("message")
    private String message;
//
//    public int getResponse() {
//        return response;
//    }
//
//    public void setResponse(int response) {
//        this.response = response;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
