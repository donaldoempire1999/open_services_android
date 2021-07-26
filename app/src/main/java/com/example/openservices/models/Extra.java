package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Extra {

    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;
    @SerializedName("location")
    private String location;
    @SerializedName("image")
    private String image;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
