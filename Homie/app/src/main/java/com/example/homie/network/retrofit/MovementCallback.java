package com.example.homie.network.retrofit;

import com.example.homie.DRO.MovementDRO;
import com.example.homie.viewModels.MovementSensorCallBack;

import retrofit2.Callback;

public interface MovementCallback extends Callback<MovementDRO> {

    void start(String token, String userId,  MovementSensorCallBack callBack);
}
