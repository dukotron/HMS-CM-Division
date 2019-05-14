package com.example.homie.viewModels;

import com.example.homie.DRO.DevicesListDRO;
import com.example.homie.DRO.SensorDRO;

public interface DevicesCallback {
    void onDelete(int statusCode);
    void onReturnAllDevices(DevicesListDRO devices);
    void onReturnScore(SensorDRO scoreData);
}
