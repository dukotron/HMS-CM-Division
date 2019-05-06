package com.example.homie.network.retrofit;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.viewModels.SensorDataCallBack;

import retrofit2.Callback;

public interface MovementCallback extends Callback<SensorDRO> {

    void start(String token, String userId,  SensorDataCallBack callBack);
}
