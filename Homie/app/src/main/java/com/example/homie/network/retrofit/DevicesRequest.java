package com.example.homie.network.retrofit;

import com.example.homie.DRO.DevicesListDRO;
import com.example.homie.viewModels.DevicesCallback;
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

public class DevicesRequest {

    public void getAllDevices(String token, String userId, final DevicesCallback callback) {
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
        Call<DevicesListDRO> call = client.getUserDevices(token, userId);
        call.enqueue(new Callback<DevicesListDRO>() {
            @Override
            public void onResponse(Call<DevicesListDRO> call, Response<DevicesListDRO> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onReturnAllDevices(response.body());
                }
            }

            @Override
            public void onFailure(Call<DevicesListDRO> call, Throwable t) {

            }
        });
    }

    public void deleteDevice(String token, String deviceId, final DevicesCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<Integer> call = client.deleteDeviceForUser(token, deviceId);
        call.enqueue(new Callback<Integer>() {
            @Override
            public void onResponse(Call<Integer> call, Response<Integer> response) {
                if (response.isSuccessful() && response.body() != null) {
                    callback.onDelete(response.body());
                }
            }

            @Override
            public void onFailure(Call<Integer> call, Throwable t) {

            }
        });
    }
}
