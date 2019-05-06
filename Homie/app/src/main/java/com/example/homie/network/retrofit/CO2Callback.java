package com.example.homie.network.retrofit;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.viewModels.SensorDataCallBack;

import retrofit2.Callback;

public interface CO2Callback extends Callback<SensorDRO> {

    void start(String token, String userId,  SensorDataCallBack callBack);
}
