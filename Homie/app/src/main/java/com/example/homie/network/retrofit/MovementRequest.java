package com.example.homie.network.retrofit;


import android.util.Log;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.viewModels.SensorDataCallBack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Date;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class MovementRequest implements SensorCallback {

    SensorDataCallBack callBack;

    @Override
    public void start(String token, String userId, SensorDataCallBack callBack, String dateFrom, String dateTo) {
        this.callBack = callBack;

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder().addInterceptor(interceptor);

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<SensorDRO> call = client.getMovementData(token, userId, dateFrom, dateTo);
        call.enqueue(this);

    }


    @Override
    public void onResponse(Call<SensorDRO> call, Response<SensorDRO> response) {
        if (response.code() == 200) {
            callBack.onReturnMovementData(response.body());
        }
    }

    @Override
    public void onFailure(Call<SensorDRO> call, Throwable t) {
        Log.d("MovementRequest", t.toString());
    }
}
