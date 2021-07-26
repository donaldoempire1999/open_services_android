package com.example.openservices.responses;

import com.example.openservices.models.Publication;
import com.example.openservices.models.PublicationDetail;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PublicationDetailsResponse {

    @SerializedName("publications")
    private ArrayList<PublicationDetail> publications;

    public ArrayList<PublicationDetail> getPublications() {
        return publications;
    }

    public void setPublications(ArrayList<PublicationDetail> publicationDetails) {
        this.publications = publicationDetails;
    }
}
