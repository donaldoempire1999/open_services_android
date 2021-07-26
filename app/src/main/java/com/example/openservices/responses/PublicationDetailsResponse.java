package com.example.openservices.responses;

import com.example.openservices.models.Publication;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PublicationDetailsResponse {

    @SerializedName("publications")
    private ArrayList<Publication> publications;

    public ArrayList<Publication> getPublications() {
        return publications;
    }

    public void setPublications(ArrayList<Publication> publications) {
        this.publications = publications;
    }
}
