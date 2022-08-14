package com.up.socketservice.model.server.gps;

public class GpsPackage {
    public String driverIdentification;

    public String driverID;

    public String latitude;

    public String longitude;

    public String type;

    public String getDriverIdentification() {
        return driverIdentification;
    }

    public void setDriverIdentification(String driverIdentification) {
        this.driverIdentification = driverIdentification;
    }

    public String getDriverID() {
        return driverID;
    }

    public void setDriverID(String driverID) {
        this.driverID = driverID;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "GpsMeassage{" +
                "driverIdentification='" + driverIdentification + '\'' +
                ", driverID='" + driverID + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public String getStringLatLong() {
        return latitude + "," + longitude;
    }
}
