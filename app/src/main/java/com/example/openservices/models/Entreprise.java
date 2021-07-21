package com.example.openservices.models;

import java.util.Date;

public class Entreprise {
    private String name;
    private Date creation_date;

    public Entreprise() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }
}
