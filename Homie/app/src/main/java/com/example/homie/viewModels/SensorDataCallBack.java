package com.example.homie.viewModels;

import com.example.homie.DRO.SensorDRO;

public interface SensorDataCallBack {

    void onReturnMovementDailyData(SensorDRO response);
    void onReturnMovementHourlyData(SensorDRO response);
    void onReturnCo2DailyData(SensorDRO response);
    void onReturnCo2HourlyData(SensorDRO response);
    void onReturnHumidityDailyData(SensorDRO response);
    void onReturnHumidityHourlyData(SensorDRO response);
    void onReturnTemperatureDailyData(SensorDRO response);
    void onReturnTemperatureHourlyData(SensorDRO response);
    void onReturnLightDailyData(SensorDRO response);
    void onReturnLightHourlyData(SensorDRO response);
}
