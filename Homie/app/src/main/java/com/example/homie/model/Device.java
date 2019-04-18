package com.example.homie.model;

public class Device {

    public String serialNo;
    public String location;
    public String status;

    public Device(){


    }

    public Device(String location, String status){
        location = location;
        status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
