package com.BillingApp.Model;

import java.util.Date;

public class Booking {
    private String movieName;
    private String date;
    private String hour;
    private Seat seat;
    private double price;

    public Booking(){

    }

    public Booking(String movieName, String date, String hour, Seat seat,double price) {
        this.movieName = movieName;
        this.date = date;
        this.hour = hour;
        this.seat = seat;
        this.price= price;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "movieName='" + movieName + '\'' +
                ", date=" + date +
                ", hour='" + hour + '\'' +
                ", seat=" + seat +
                '}';
    }


}
