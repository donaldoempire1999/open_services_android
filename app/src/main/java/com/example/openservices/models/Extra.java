package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Extra {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;

    public Extra() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
