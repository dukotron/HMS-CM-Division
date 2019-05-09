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

}
