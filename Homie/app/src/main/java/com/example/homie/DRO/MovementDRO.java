package com.example.homie.DRO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MovementDRO implements Serializable {

    @Expose
    @SerializedName("StatusCode")
    private int statusCode;
    @SerializedName("SensorDataList")
    @Expose
    private List<SensorData> sensorData;

    public MovementDRO(int statusCode) {
        this.statusCode = statusCode;
    }

    public MovementDRO(int statusCode, List<SensorData> sensorData) {
        this.statusCode = statusCode;
        this.sensorData = sensorData;
    }

    public int getStatusCode() {
        return statusCode;
    }


    public List<SensorData> getSensorDataList() {return sensorData;}

    @Override
    public String toString() {
        return "MovementDRO{" +
                "statusCode=" + statusCode +
                ", sensorData=" + sensorData +
                '}';
    }
}
