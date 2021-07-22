package com.example.openservices.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class User {

    @SerializedName("category")
    private Category category;
    @SerializedName("address")
    private Address address;
//    @SerializedName("person")
//    private Person person;
    @SerializedName("entreprise")
    private Entreprise entreprise;
    @SerializedName("cv")
    private Cv cv;
    @SerializedName("register_date")
    private String register_date;
    @SerializedName("status")
    private String status;
    @SerializedName("contracts")
    private ArrayList<Contract> contracts;
    @SerializedName("_id")
    private String _id;
    @SerializedName("publications")
    private ArrayList<String> publications;
    @SerializedName("email")
    private String email;
    @SerializedName("phone_number")
    private String phone_number;
    @SerializedName("__v")
    private int version;
//    @SerializedName("image_url")
//    private String image_url;

    public User() {
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Entreprise getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(Entreprise entreprise) {
        this.entreprise = entreprise;
    }

    public Cv getCv() {
        return cv;
    }

    public void setCv(Cv cv) {
        this.cv = cv;
    }

    public String getRegister_date() {
        return register_date;
    }

    public void setRegister_date(String register_date) {
        this.register_date = register_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(ArrayList<Contract> contracts) {
        this.contracts = contracts;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public ArrayList<String> getPublications() {
        return publications;
    }

    public void setPublications(ArrayList<String> publications) {
        this.publications = publications;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
