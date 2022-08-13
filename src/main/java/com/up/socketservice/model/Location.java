package com.up.socketservice.model;

public class Location {
    public double latitude,longitude;
    public String name;

    public Location() {
    }

    public Location(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
}
