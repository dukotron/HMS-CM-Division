package com.example.homie.network.retrofit;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class NotificationRequest implements Callback<Integer> {

    public void setNotificationToken(String tokenAuth, String userId, String notificationToken) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<Integer> call = client.setNotificationToken(tokenAuth, userId, notificationToken);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Integer> call, Response<Integer> response) {

    }

    @Override
    public void onFailure(Call<Integer> call, Throwable t) {
        Log.d("NotificationRequest", t.toString());
    }
}
