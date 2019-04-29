package com.example.homie.network.retrofit;


import android.util.Log;

import com.example.homie.DRO.MovementDRO;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class MovementRequest implements MovementCallback {

    @Override
    public void start(int userId) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<List<MovementDRO>> call = client.getMovementData(userId);
        call.enqueue(this);
    }


    @Override
    public void onResponse(Call<List<MovementDRO>> call, Response<List<MovementDRO>> response) {
        if (response.code() == 200) {
            for (MovementDRO movementDROs : response.body())
                Log.d("MovementRequest", movementDROs.toString());
        }
    }

    @Override
    public void onFailure(Call<List<MovementDRO>> call, Throwable t) {

    }
}
