package com.example.homie.DTO;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddDeviceDTO {

    @SerializedName("deviceId")
    @Expose
    private String deviceId;

    @SerializedName("owner")
    @Expose
    private String userId;

    @SerializedName("location")
    @Expose
    private String deviceLocation;

    public AddDeviceDTO(String deviceId, String userId, String deviceLocation) {
        this.deviceId = deviceId;
        this.userId = userId;
        this.deviceLocation = deviceLocation;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDeviceLocation() {
        return deviceLocation;
    }

    public void setDeviceLocation(String deviceLocation) {
        this.deviceLocation = deviceLocation;
    }
}
