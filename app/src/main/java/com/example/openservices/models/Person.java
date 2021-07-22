package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Person {

    @SerializedName("first_name")
    private String first_name;
    @SerializedName("second_name")
    private String second_name;
    @SerializedName("birthday")
    private String birthday;

    public Person() {
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public void setSecond_name(String second_name) {
        this.second_name = second_name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
