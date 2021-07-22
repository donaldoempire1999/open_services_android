package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;


public class Period {

    @SerializedName("start_String")
    private String start_String;
    @SerializedName("end_String")
    private String end_String;

    public Period() {
    }

    public String getStart_String() {
        return start_String;
    }

    public void setStart_String(String start_String) {
        this.start_String = start_String;
    }

    public String getEnd_String() {
        return end_String;
    }

    public void setEnd_String(String end_String) {
        this.end_String = end_String;
    }
}
