package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Contract {

    @SerializedName("_id")
    private String _id;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }
}
