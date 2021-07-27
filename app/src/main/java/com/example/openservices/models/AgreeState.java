package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class AgreeState {

    @SerializedName("requester")
    private UserAccordance requester;
    @SerializedName("provider")
    private UserAccordance provider;
    @SerializedName("task")
    private String task;

    public UserAccordance getRequester() {
        return requester;
    }

    public void setRequester(UserAccordance requester) {
        this.requester = requester;
    }

    public UserAccordance getProvider() {
        return provider;
    }

    public void setProvider(UserAccordance provider) {
        this.provider = provider;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
