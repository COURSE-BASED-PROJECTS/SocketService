package com.up.socketservice.dto;

public class DriverDto {

    public Integer driverId;

    public String driverName;

    public String identification;

    public String phoneNumber;

    public Long balance;

    public int ride_count;

    public int taxi_id;

    public DriverDto() {
    }

    public DriverDto(Integer driverId, String driverName, String identification, String phoneNumber, Long balance, int ride_count, int taxi_id) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.identification = identification;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.ride_count = ride_count;
        this.taxi_id = taxi_id;
    }

    public Integer getDriverId() {
        return driverId;
    }

    public void setDriverId(Integer driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public int getRide_count() {
        return ride_count;
    }

    public void setRide_count(int ride_count) {
        this.ride_count = ride_count;
    }

    public int getTaxi_id() {
        return taxi_id;
    }

    public void setTaxi_id(int taxi_id) {
        this.taxi_id = taxi_id;
    }

    @Override
    public String toString() {
        return "DriverDto{" +
                "driverId=" + driverId +
                ", driverName='" + driverName + '\'' +
                ", identification='" + identification + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", balance=" + balance +
                ", ride_count=" + ride_count +
                ", taxi_id=" + taxi_id +
                '}';
    }
}
