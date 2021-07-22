package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Media {

    @SerializedName("type")
    private String type;
    @SerializedName("url")
    private String url;

    public Media() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
