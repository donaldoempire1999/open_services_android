package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PublicationDetail {

    @SerializedName("task_description")
    private TaskDescription task_description;
    @SerializedName("likes")
    private int likes;
    @SerializedName("followers")
    private ArrayList<User> followers;
    @SerializedName("contract_for_post")
    private Contract contract_for_post;
    @SerializedName("state")
    private String state;
    @SerializedName("publication_date")
    private Contract publication_date;
    @SerializedName("_id")
    private Contract _id;
    @SerializedName("author")
    private String author;
    @SerializedName("medias")
    private ArrayList<Media> medias;
    @SerializedName("comments")
    private ArrayList<Comment> comments;
    @SerializedName("__v")
    private int __v;

    public PublicationDetail() {
    }

    public TaskDescription getTask_description() {
        return task_description;
    }

    public void setTask_description(TaskDescription task_description) {
        this.task_description = task_description;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public Contract getContract_for_post() {
        return contract_for_post;
    }

    public void setContract_for_post(Contract contract_for_post) {
        this.contract_for_post = contract_for_post;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Contract getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(Contract publication_date) {
        this.publication_date = publication_date;
    }

    public Contract get_id() {
        return _id;
    }

    public void set_id(Contract _id) {
        this._id = _id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
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

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}
