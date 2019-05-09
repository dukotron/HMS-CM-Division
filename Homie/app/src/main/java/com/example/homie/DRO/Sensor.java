package com.example.homie.DRO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Sensor {

    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Data")
    @Expose
    private List<SensorData> data;

    public Sensor(String type, List<SensorData> data) {
        this.type = type;
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<SensorData> getData() {
        return data;
    }

    public void setData(List<SensorData> data) {
        this.data = data;
    }
}
