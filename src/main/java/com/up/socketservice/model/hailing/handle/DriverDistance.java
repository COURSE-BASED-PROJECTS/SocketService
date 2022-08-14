package com.up.socketservice.model.hailing.handle;

public class DriverDistance {
    private String idDriver;

    private int distance;

    public String getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(String idDriver) {
        this.idDriver = idDriver;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }


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
