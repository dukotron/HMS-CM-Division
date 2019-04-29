package com.example.homie.DRO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

class SensorData implements Serializable {

    @SerializedName("TimeStamp")
    private String timeStamp;
    @SerializedName("Value")
    private int value;


    public SensorData(String timeStamp, int value) {
        this.timeStamp = timeStamp;
        this.value = value;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "timeStamp='" + timeStamp + '\'' +
                ", value=" + value +
                '}';
    }
}
