package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Cv {

    @SerializedName("bio")
    private String bio;
    @SerializedName("jobs")
    private ArrayList<Job> jobs;
    @SerializedName("extra")
    private ArrayList<Extra> extra;

    public Cv() {
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<Job> getJobs() {
        return jobs;
    }

    public void setJobs(ArrayList<Job> jobs) {
        this.jobs = jobs;
    }

    public ArrayList<Extra> getExtra() {
        return extra;
    }

    public void setExtra(ArrayList<Extra> extra) {
        this.extra = extra;
    }
}
