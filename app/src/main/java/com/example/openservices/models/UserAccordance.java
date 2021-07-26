package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class UserAccordance {

    @SerializedName("state")
    private boolean state;
    @SerializedName("date")
    private String date;

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
