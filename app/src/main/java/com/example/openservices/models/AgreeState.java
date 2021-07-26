package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class AgreeState {

    @SerializedName("requester")
    private UserAccordance requester;
    @SerializedName("provider")
    private UserAccordance provider;
    @SerializedName("task")
    private String task;

}
