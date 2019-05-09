package com.example.homie.network.retrofit;

import android.util.Log;

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

public class DevicesRequest implements Callback<DevicesListDRO> {

    private DevicesCallback callback;

    public void getAllDevices(String token, String userId,DevicesCallback callback){
        this.callback = callback;

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<DevicesListDRO> call = client.getUserDevices(token, userId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<DevicesListDRO> call, Response<DevicesListDRO> response) {
        if(response.isSuccessful() && response.body() != null){
            callback.onReturn(response.body());
        }
    }

    @Override
    public void onFailure(Call<DevicesListDRO> call, Throwable t) {

    }
}
