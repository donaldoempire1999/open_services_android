package com.example.openservices.models;

import java.util.ArrayList;

public class Publication {

    private int likes;
    private ArrayList<Media> medias;
    private ArrayList<Comment> comments;
    private TaskDescription taskDescription;
    private ArrayList<User> followers;
    private String state;
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
        return taskDescription;
    }

    public void setTaskDescription(TaskDescription taskDescription) {
        this.taskDescription = taskDescription;
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

    public Contract getContract_for_post() {
        return contract_for_post;
    }

    public void setContract_for_post(Contract contract_for_post) {
        this.contract_for_post = contract_for_post;
    }
}
