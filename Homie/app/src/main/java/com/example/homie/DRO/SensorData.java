package com.example.homie.DRO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class SensorData implements Serializable {

    @SerializedName("TimeStamp")
    private Date timeStamp;
    @SerializedName("Value")
    private int value;


    public SensorData(Date timeStamp, int value) {
        this.timeStamp = timeStamp;
        this.value = value;
    }

    public Date getTimeStamp() {
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
