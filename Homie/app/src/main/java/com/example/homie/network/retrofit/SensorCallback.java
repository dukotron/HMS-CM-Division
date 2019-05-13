package com.example.homie.network.retrofit;

import com.example.homie.viewModels.SensorDataCallBack;

public interface SensorCallback {

    void getDailyData(String token, String deviceId, String userId, SensorDataCallBack callBack, String dateFrom, String dateTo);

    void getHourlyData(String token, String deviceId, String userId, SensorDataCallBack callBack, String dateFrom, String dateTo);

}
