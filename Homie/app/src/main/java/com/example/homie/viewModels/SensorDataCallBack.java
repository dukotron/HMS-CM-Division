package com.example.homie.viewModels;

import com.example.homie.DRO.SensorDRO;

public interface SensorDataCallBack {

    void onReturnMovementData(SensorDRO response);
    void onReturnCo2Data(SensorDRO response);
    void onReturnHumidityData(SensorDRO response);
    void onReturnTemperatureData(SensorDRO response);
}
