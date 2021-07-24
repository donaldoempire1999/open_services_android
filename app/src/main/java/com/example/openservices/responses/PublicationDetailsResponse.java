package com.example.openservices.responses;

import com.example.openservices.models.Publication;
import com.google.gson.annotations.SerializedName;

public class PublicationDetailsResponse {

    @SerializedName("publications")
    private Publication publication;

    public Publication getPublication() {
        return publication;
    }

    public void setPublication(Publication publication) {
        this.publication = publication;
    }
}
