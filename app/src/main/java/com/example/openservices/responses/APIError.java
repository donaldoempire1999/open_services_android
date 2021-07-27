package com.example.openservices.responses;

import com.google.gson.annotations.SerializedName;

public class APIError {

    @SerializedName("error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
