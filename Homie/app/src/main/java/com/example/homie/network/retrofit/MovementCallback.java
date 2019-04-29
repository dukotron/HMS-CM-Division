package com.example.homie.network.retrofit;

import com.example.homie.DRO.MovementDRO;
import com.example.homie.viewModel.MovementSensorCallBack;

import java.util.List;

import retrofit2.Callback;

public interface MovementCallback extends Callback<MovementDRO> {

    void start(int userId, MovementSensorCallBack callBack);
}
