package com.up.socketservice.dto;

import java.time.LocalDateTime;

public class HailingDto {
    public Integer hailing_id;

    public String client_id;

    public Integer driver_id;

    public Double distance;

    public Long time_during;

    public Double cost;

    public String time_start;

    public String scope;

    public String status;

    public Integer picking_address;

    public Integer arriving_address;

    public Integer car_type;

    public HailingDto() {
    }

    public HailingDto(Integer hailing_id, String client_id, Integer driver_id, Double distance, Long time_during, Double cost, String time_start, String scope, String status, Integer picking_address, Integer arriving_address, Integer car_type) {
        this.hailing_id = hailing_id;
        this.client_id = client_id;
        this.driver_id = driver_id;
        this.distance = distance;
        this.time_during = time_during;
        this.cost = cost;
        this.time_start = time_start;
        this.scope = scope;
        this.status = status;
        this.picking_address = picking_address;
        this.arriving_address = arriving_address;
        this.car_type = car_type;
    }

    public Integer getHailing_id() {
        return hailing_id;
    }

    public String getClient_id() {
        return client_id;
    }

    public Integer getDriver_id() {
        return driver_id;
    }

    public Double getDistance() {
        return distance;
    }

    public Long getTime_during() {
        return time_during;
    }

    public Double getCost() {
        return cost;
    }

    public String getTime_start() {
        return time_start;
    }

    public String getScope() {
        return scope;
    }

    public String getStatus() {
        return status;
    }

    public Integer getPicking_address() {
        return picking_address;
    }

    public Integer getArriving_address() {
        return arriving_address;
    }

    public Integer getCar_type() {
        return car_type;
    }
}
