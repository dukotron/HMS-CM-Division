package com.example.homie.DRO;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

public class SensorData implements Serializable {

    @SerializedName("TimeStamp")
    private Date date;
    @SerializedName("Value")
    private float value;


    public SensorData(Date date, int value) {
        this.date = date;
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public float getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "SensorData{" +
                "date='" + date + '\'' +
                ", value=" + value +
                '}';
    }
}
