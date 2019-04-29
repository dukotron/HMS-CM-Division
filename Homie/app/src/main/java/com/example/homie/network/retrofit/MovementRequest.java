package com.example.homie.network.retrofit;


import android.util.Log;

import com.example.homie.DRO.MovementDRO;
import com.example.homie.viewModel.MovementSensorCallBack;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class MovementRequest implements MovementCallback {

    MovementSensorCallBack callBack;

    @Override
    public void start(int userId, MovementSensorCallBack callBack) {
        this.callBack = callBack;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<MovementDRO> call = client.getMovementData(userId);
        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<MovementDRO> call, Response<MovementDRO> response) {
        if (response.code() == 200) {
            callBack.onReturn(response.body());
            Log.d("MovementRequest", response.body().toString());
        }
    }

    @Override
    public void onFailure(Call<MovementDRO> call, Throwable t) {

    }
}
