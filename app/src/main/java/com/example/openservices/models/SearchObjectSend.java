package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class SearchObjectSend {

    @SerializedName("collection")
    private String collection;
    @SerializedName("query_string")
    private String query_string;

    public SearchObjectSend() {
    }

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public String getQuery_string() {
        return query_string;
    }

    public void setQuery_string(String query_string) {
        this.query_string = query_string;
    }
}
