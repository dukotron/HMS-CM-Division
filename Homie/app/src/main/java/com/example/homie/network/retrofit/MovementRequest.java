package com.example.homie.network.retrofit;


import android.util.Log;

import com.example.homie.DRO.MovementDRO;
import com.example.homie.viewModels.MovementSensorCallBack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class MovementRequest implements MovementCallback {

    MovementSensorCallBack callBack;

    @Override
    public void start(String token, String userId, MovementSensorCallBack callBack) {
        this.callBack = callBack;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<MovementDRO> call = client.getMovementData(token, userId);
        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<MovementDRO> call, Response<MovementDRO> response) {
        if (response.code() == 200) {
            callBack.onReturn(response.body());
        }
    }

    @Override
    public void onFailure(Call<MovementDRO> call, Throwable t) {}
}
