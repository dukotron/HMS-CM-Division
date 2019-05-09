package com.example.homie.network.retrofit;

import android.util.Log;

import com.example.homie.DRO.DevicesListDRO;
import com.example.homie.DTO.AddDeviceDTO;
import com.example.homie.viewModels.AddDeviceCallback;
import com.example.homie.viewModels.DevicesCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class AddDeviceRequest implements Callback<Integer>{

    private AddDeviceCallback addDeviceCallback;


    public void addDevice(String token, AddDeviceDTO deviceInfo, AddDeviceCallback callback) {
        this.addDeviceCallback = callback;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<Integer> call = client.addDeviceForUser(token, deviceInfo);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Integer> call, Response<Integer> response) {
        if (response.isSuccessful() && response.body() != null) {
            addDeviceCallback.onReturn(response.body());
        }
        Log.d("ADDING DEVICE",""+response.body());
    }

    @Override
    public void onFailure(Call<Integer> call, Throwable t) {

    }
}
