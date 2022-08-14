package com.up.socketservice.model.hailing;

import com.up.socketservice.model.hailing.handle.HandlePackageContext;

import java.time.LocalDateTime;

public class HailingPackage {
    private Location locationStart;
    private Location locationEnd;
    private long timeDuring;
    private LocalDateTime timeStart;
    private Double distance;
    private Double cost;

    public Location getLocationStart() {
        return locationStart;
    }

    public void setLocationStart(Location locationStart) {
        this.locationStart = locationStart;
    }

    public Location getLocationEnd() {
        return locationEnd;
    }

    public void setLocationEnd(Location locationEnd) {
        this.locationEnd = locationEnd;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public long getTimeDuring() {
        return timeDuring;
    }

    public void setTimeDuring(long timeDuring) {
        this.timeDuring = timeDuring;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public int getCarType() {
        return carType;
    }

    public void setCarType(int carType) {
        this.carType = carType;
    }

    public int carType;

}
