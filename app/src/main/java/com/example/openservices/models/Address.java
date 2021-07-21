package com.example.openservices.models;

public class Address {
    private String country;
    private String region;
    private String city;
    private String quarter;
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
