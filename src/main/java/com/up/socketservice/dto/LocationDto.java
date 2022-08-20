package com.up.socketservice.dto;

public class LocationDto {
    public Integer location_id;

    public String location_name;

    public Integer count;

    public String client_id;

    public Double latitude;

    public Double longitude;

    public LocationDto(Integer location_id, String location_name, Integer count, String client_id, Double latitude, Double longitude) {
        this.location_id = location_id;
        this.location_name = location_name;
        this.count = count;
        this.client_id = client_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public LocationDto() {
    }

    public Integer getLocation_id() {
        return location_id;
    }

    public String getLocation_name() {
        return location_name;
    }

    public Integer getCount() {
        return count;
    }

    public String getClient_id() {
        return client_id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }
}
