package com.example.openservices.responses;

import com.google.gson.annotations.SerializedName;

public class PublicationSaveResponse {

    @SerializedName("message")
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
