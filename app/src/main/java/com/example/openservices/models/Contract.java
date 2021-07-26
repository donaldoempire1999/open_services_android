package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Contract {

    @SerializedName("requester")
    private User requester;
    @SerializedName("provider")
    private AgreeState provider;
    @SerializedName("publication")
    private Publication publication;
    @SerializedName("agree_state")
    private AgreeState agree_state;

    @SerializedName("close")

    public User getRequester() {
        return requester;
    }

    public void setRequester(User requester) {
        this.requester = requester;
    }

    public AgreeState getProvider() {
        return provider;
    }

    public void setProvider(AgreeState provider) {
        this.provider = provider;
    }

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    public AgreeState getAgree_state() {
        return agree_state;
    }

    public void setAgree_state(AgreeState agree_state) {
        this.agree_state = agree_state;
    }
}
