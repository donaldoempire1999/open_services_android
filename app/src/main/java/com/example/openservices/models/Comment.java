package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class Comment {

    @SerializedName("user_who_comment")
    private User user_who_comment;
    @SerializedName("text")
    private String text;

    public Comment() {
    }

    public User getUser_who_comment() {
        return user_who_comment;
    }

    public void setUser_who_comment(User user_who_comment) {
        this.user_who_comment = user_who_comment;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
