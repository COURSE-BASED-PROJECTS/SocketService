package com.up.socketservice.dto;

public class LocationDto {
    public  Integer location_id;
    public String location_name;

    public Integer count;

    public double latitude;

    public double longitude;

    public LocationDto(Integer location_id, String location_name, Integer count, double latitude, double longitude) {
        this.location_id = location_id;
        this.location_name = location_name;
        this.count = count;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationDto() {
    }

    public String getLocation_name() {
        return location_name;
    }

    public void setLocation_name(String location_name) {
        this.location_name = location_name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}
