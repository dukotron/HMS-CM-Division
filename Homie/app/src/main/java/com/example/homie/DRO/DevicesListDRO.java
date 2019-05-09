package com.example.homie.DRO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DevicesListDRO {

    @SerializedName("StatusCode")
    @Expose
    private int statusCode;
    @SerializedName("UserDeviceData")
    @Expose
    private List<DeviceData> devices;

    public DevicesListDRO(int statusCode, List<DeviceData> devices) {
        this.statusCode = statusCode;
        this.devices = devices;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public List<DeviceData> getDevices() {
        return devices;
    }

    public void setDevices(List<DeviceData> devices) {
        this.devices = devices;
    }
}
