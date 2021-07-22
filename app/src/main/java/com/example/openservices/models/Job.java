package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Job {

    @SerializedName("_id")
    private String _id;
    @SerializedName("title")
    private String title;
    @SerializedName("description")
    private String description;

    public Job() {
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
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
