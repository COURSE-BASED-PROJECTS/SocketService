package com.up.socketservice.model;

import java.time.LocalDateTime;

public class OrderMessage {
    private OrderMessage.OrderType type;
    private String phoneNumber;
    private String cusName;
    private String pickingAddress;
    private Float lngPickingAddr;
    private Float latPickingAddr;
    private String arrivingAddress;
    private Float lngArrivingAddr;
    private Float latArrivingAddr;
    private Float distance;
    private int duration;
    private Float cost;
    private LocalDateTime bookingTime;

    public enum OrderType {
        CONNECTED,
        SENT,
        DISCONNECTED
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCusName() {
        return cusName;
    }

    public void setCusName(String cusName) {
        this.cusName = cusName;
    }

    public String getPickingAddress() {
        return pickingAddress;
    }

    public void setPickingAddress(String pickingAddress) {
        this.pickingAddress = pickingAddress;
    }

    public Float getLngPickingAddr() {
        return lngPickingAddr;
    }

    public void setLngPickingAddr(Float lngPickingAddr) {
        this.lngPickingAddr = lngPickingAddr;
    }

    public Float getLatPickingAddr() {
        return latPickingAddr;
    }

    public void setLatPickingAddr(Float latPickingAddr) {
        this.latPickingAddr = latPickingAddr;
    }

    public String getArrivingAddress() {
        return arrivingAddress;
    }

    public void setArrivingAddress(String arrivingAddress) {
        this.arrivingAddress = arrivingAddress;
    }

    public Float getLngArrivingAddr() {
        return lngArrivingAddr;
    }

    public void setLngArrivingAddr(Float lngArrivingAddr) {
        this.lngArrivingAddr = lngArrivingAddr;
    }

    public Float getLatArrivingAddr() {
        return latArrivingAddr;
    }

    public void setLatArrivingAddr(Float latArrivingAddr) {
        this.latArrivingAddr = latArrivingAddr;
    }

    public Float getDistance() {
        return distance;
    }

    public void setDistance(Float distance) {
        this.distance = distance;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public Float getCost() {
        return cost;
    }

    public void setCost(Float cost) {
        this.cost = cost;
    }

    public LocalDateTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(LocalDateTime bookingTime) {
        this.bookingTime = bookingTime;
    }

    @Override
    public String toString() {
        return "OrderMessage{" +
                "type=" + type +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", cusName='" + cusName + '\'' +
                ", pickingAddress='" + pickingAddress + '\'' +
                ", lngPickingAddr=" + lngPickingAddr +
                ", latPickingAddr=" + latPickingAddr +
                ", arrivingAddress='" + arrivingAddress + '\'' +
                ", lngArrivingAddr=" + lngArrivingAddr +
                ", latArrivingAddr=" + latArrivingAddr +
                ", distance=" + distance +
                ", duration=" + duration +
                ", cost=" + cost +
                ", bookingTime=" + bookingTime +
                '}';
    }
}
