package com.BillingApp.Model;


public class Movie {
    private String name;
    private String duration;
    private String description;
    private double price;
    private String trailer;
    private String startDate;
    private String endDate;
    private String[] times ={"hh:mm", "hh:mm","hh:mm"};

    public Movie(){

    }
    public Movie(String name, String duration, String description, String trailer,double price,String startDate,String endDate,String[]times) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.price=price;
        this.trailer=trailer;
        this.startDate=startDate;
        this.endDate=endDate;
        this.times=times;
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

    public String toString(){
        return this.name + "\t"+ duration+ "\t" + price;
    }
    String getStartDate() {

        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String[] getTimes() {
        return times;
    }

    public void setTimes(String[] times) {
        this.times = times;
    }
}
