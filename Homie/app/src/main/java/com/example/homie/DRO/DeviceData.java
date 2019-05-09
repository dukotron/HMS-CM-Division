package com.example.homie.DRO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DeviceData {

    @SerializedName("DeviceId")
    @Expose
    private String id;
    @SerializedName("Location")
    @Expose
    private String location;
    @SerializedName("Sensors")
    @Expose
    private List<Sensor> sensors;

    public DeviceData(String id, String location, List<Sensor> sensors) {
        this.id = id;
        this.location = location;
        this.sensors = sensors;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
