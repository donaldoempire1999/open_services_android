package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Category {

    @SerializedName("type_user")
    private String type_user;
    @SerializedName("role")
    private String role;

    public Category() {
    }

    public String getType_user() {
        return type_user;
    }

    public void setType_user(String type_user) {
        this.type_user = type_user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
