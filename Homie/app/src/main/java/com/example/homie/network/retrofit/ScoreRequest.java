package com.example.homie.network.retrofit;

import android.util.Log;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.viewModels.DevicesCallback;
import com.example.homie.viewModels.SensorDataCallBack;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class ScoreRequest {

    public void getHourlyScore(String token, String userId, final DevicesCallback callBack, String dateFrom, String dateTo) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder().addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<SensorDRO> call = client.getHourlyScore(token, userId, dateFrom, dateTo);
        call.enqueue(new Callback<SensorDRO>() {
            @Override
            public void onResponse(Call<SensorDRO> call, Response<SensorDRO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onReturnScore(response.body());
                }
            }

            @Override
            public void onFailure(Call<SensorDRO> call, Throwable t) {
                Log.d("Co2Request", t.toString());
            }
        });
    }

}