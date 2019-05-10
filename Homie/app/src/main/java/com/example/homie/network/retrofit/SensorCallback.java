package com.example.homie.network.retrofit;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.viewModels.SensorDataCallBack;

import retrofit2.Callback;

public interface SensorCallback{

    void getDailyData(String token, String userId, SensorDataCallBack callBack, String dateFrom, String dateTo);
    void getHourlyData(String token, String userId, SensorDataCallBack callBack, String dateFrom, String dateTo);

}
