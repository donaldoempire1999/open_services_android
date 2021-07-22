package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

public class TaskDescription {

    @SerializedName("difficulty")
    private String difficulty;
    @SerializedName("priority")
    private int priority;
    @SerializedName("description")
    private String description;
    @SerializedName("points")
    private int points;
    @SerializedName("base_amount")
    private int base_amount;
    @SerializedName("period")
    private Period period;

    public TaskDescription() {
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getBase_amount() {
        return base_amount;
    }

    public void setBase_amount(int base_amount) {
        this.base_amount = base_amount;
    }

    public Period getPeriod() {
        return period;
    }

    public void setPeriod(Period period) {
        this.period = period;
    }
}
