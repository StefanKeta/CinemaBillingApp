package com.BillingApp.Model;


import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private String name;
    private String duration;
    private String description;
    private double price;
    private String trailer;
    private String startDate;
    private String endDate;
    private List<String> times =new ArrayList<String>();
    private List<Seat> seats= new ArrayList<>();

    public Movie(){
    }

    public Movie(String name, String duration, String description, String trailer,double price,String startDate,String endDate,List<String> times) {
        this.name = name;
        this.duration = duration;
        this.description = description;
        this.price=price;
        this.trailer=trailer;
        this.startDate=startDate;
        this.endDate=endDate;
        this.times=times;
        for(char c = 'A' ;c<='C';c++)
            for(int i=1;i<=6;i++)
                seats.add(new Seat(c,i));
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


    public String getStartDate() {

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

    public List<String> getTimes() {
        return times;
    }

    public void setTimes(List<String> times) {
        this.times = times;
    }

    @Override
    public String toString() {
        return  name + '\t' +
                duration + '\t'  +
                 price
                ;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addSeats(Seat seat){
        seats.add(seat);
    }
}
