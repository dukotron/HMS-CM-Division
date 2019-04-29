package com.example.homie.DRO;

class SensorData {

    private String timeStamp;
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
