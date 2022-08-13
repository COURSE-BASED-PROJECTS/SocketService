package com.up.socketservice.model;

public class DriverDistance {
    public String idDriver;
    public int distance;

    public DriverDistance() {
    }

    public DriverDistance(String idDriver, int distance) {
        this.idDriver = idDriver;
        this.distance = distance;
    }

    @Override
    public String toString() {
        return "DriverDistance{" +
                "idDriver='" + idDriver + '\'' +
                ", distance=" + distance +
                '}';
    }
}
