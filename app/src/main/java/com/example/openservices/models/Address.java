package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Address {
    @SerializedName("country")
    private String country;
    @SerializedName("region")
    private String region;
    @SerializedName("city")
    private String city;
    @SerializedName("quarter")
    private String quarter;
    @SerializedName("bp")
    private int bp;

    public Address() {
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getQuarter() {
        return quarter;
    }

    public void setQuarter(String quarter) {
        this.quarter = quarter;
    }

    public int getBp() {
        return bp;
    }

    public void setBp(int bp) {
        this.bp = bp;
    }
}
