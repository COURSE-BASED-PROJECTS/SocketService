package com.up.socketservice.model;

import java.time.LocalDateTime;

public class Hailing {
    public Location locationStart;
    public Location locationEnd;
    public Double distance;
    public long timeDuring;
    public LocalDateTime timeStart;
    public Double cost;
    public int carType;

    public Hailing(Location locationStart, Location locationEnd, Double distance, long timeDuring, LocalDateTime timeStart, Double cost, int carType) {
        this.locationStart = locationStart;
        this.locationEnd = locationEnd;
        this.distance = distance;
        this.timeDuring = timeDuring;
        this.timeStart = timeStart;
        this.cost = cost;
        this.carType = carType;
    }

    public Hailing() {
    }


}
