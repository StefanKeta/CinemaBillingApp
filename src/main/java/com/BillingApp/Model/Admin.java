package com.BillingApp.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Admin {
    private String city;
    private String cinemaName;
    private String email;
    private String password;
    private List<Movie> movieList= new ArrayList<>();

    public Admin(){

    }

    public Admin(String city, String cinemaName, String email, String password) {
        this.city = city;
        this.cinemaName = cinemaName;
        this.email = email;
        this.password = password;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "city='" + city + '\'' +
                ", cinemaName='" + cinemaName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", movieList=" + movieList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if(o instanceof Admin) {
            Admin admin = (Admin) o;
            return this.getEmail().equals(admin.getEmail());
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = email.hashCode();
        result = 31 * result + password.hashCode();
        result = 31 * result + cinemaName.hashCode();
        return result;
    }

    public List<Movie> getMovieList() {
        return movieList;
    }

    public void addMovie(Movie m){
        movieList.add(m);
    }

    public void setMovieList(List<Movie> movieList) {
        this.movieList = movieList;
    }
}
