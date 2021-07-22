package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Publication {

    @SerializedName("likes")
    private int likes;
    @SerializedName("medias")
    private ArrayList<Media> medias;
    @SerializedName("comments")
    private ArrayList<Comment> comments;
    @SerializedName("task_description")
    private TaskDescription task_description;
    @SerializedName("followers")
    private ArrayList<User> followers;
    @SerializedName("state")
    private String state;
    @SerializedName("author")
    private String author;
    @SerializedName("contract_for_post")
    private Contract contract_for_post;

    public Publication() {
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public ArrayList<Media> getMedias() {
        return medias;
    }

    public void setMedias(ArrayList<Media> medias) {
        this.medias = medias;
    }

    public ArrayList<Comment> getComments() {
        return comments;
    }

    public void setComments(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    public TaskDescription getTaskDescription() {
        return task_description;
    }

    public void setTaskDescription(TaskDescription task_description) {
        this.task_description = task_description;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Contract getContract_for_post() {
        return contract_for_post;
    }

    public void setContract_for_post(Contract contract_for_post) {
        this.contract_for_post = contract_for_post;
    }
}
