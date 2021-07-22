package com.example.openservices.responses;

import android.media.session.MediaSession;

import com.google.gson.annotations.SerializedName;

public class UserSignInResponse {

    @SerializedName("userId")
    private String userId;
    @SerializedName("token")
    private String token;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
