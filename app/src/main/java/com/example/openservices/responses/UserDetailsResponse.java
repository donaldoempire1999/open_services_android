package com.example.openservices.responses;

import com.example.openservices.models.User;
import com.google.gson.annotations.SerializedName;

public class UserDetailsResponse {

    @SerializedName("user")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
