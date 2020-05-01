package com.BillingApp.Model;

import java.util.Objects;

public class Admin {
    private String city;
    private String cinemaName;
    private String email;
    private String password;

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
        return Objects.hash(city, cinemaName, email, password);
    }


}
