package com.example.homie.network.retrofit;

import android.util.Log;

import com.example.homie.DRO.SensorDRO;
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

public class CO2Request implements SensorCallback {

    @Override
    public void getDailyData(String token, String userId, final SensorDataCallBack callBack, String dateFrom, String dateTo) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<SensorDRO> call = client.getCo2DailyData(token, userId, dateFrom, dateTo);
        call.enqueue(new Callback<SensorDRO>() {
            @Override
            public void onResponse(Call<SensorDRO> call, Response<SensorDRO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onReturnCo2DailyData(response.body());
                }
            }

            @Override
            public void onFailure(Call<SensorDRO> call, Throwable t) {
                Log.d("Co2Request", t.toString());
            }
        });
    }

    @Override
    public void getHourlyData(String token, String userId, final SensorDataCallBack callBack, String dateFrom, String dateTo) {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient=new OkHttpClient.Builder().addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<SensorDRO> call = client.getCo2HourlyData(token, userId, dateFrom, dateTo);
        call.enqueue(new Callback<SensorDRO>() {
            @Override
            public void onResponse(Call<SensorDRO> call, Response<SensorDRO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callBack.onReturnCo2HourlyData(response.body());
                }
            }

            @Override
            public void onFailure(Call<SensorDRO> call, Throwable t) {
                Log.d("Co2Request", t.toString());
            }
        });
    }
}
