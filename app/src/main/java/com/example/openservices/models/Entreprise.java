package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Entreprise {

    @SerializedName("name")
    private String name;
    @SerializedName("creation_date")
    private String creation_date;

    public Entreprise() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(String creation_date) {
        this.creation_date = creation_date;
    }
}
