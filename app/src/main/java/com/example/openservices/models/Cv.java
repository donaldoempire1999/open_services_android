package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Cv {

    @SerializedName("bio")
    private String bio;
    @SerializedName("main_activity")
    private String main_activity;
    @SerializedName("title")
    private String title;
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

    public String getMain_activity() {
        return main_activity;
    }

    public void setMain_activity(String main_activity) {
        this.main_activity = main_activity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
