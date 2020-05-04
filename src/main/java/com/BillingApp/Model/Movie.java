package com.BillingApp.Model;


public class Movie {
    private String name;
    private String duration;
    private String description;
    private double price;
    private String trailer;

    public Movie(String name, String duration, String description, double price,String trailer) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.price=price;
        this.trailer=trailer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }
}
