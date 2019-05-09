package com.example.homie.network.retrofit;


import com.example.homie.DRO.SensorDRO;
import com.example.homie.viewModels.DeleteDeviceCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class DeleteDeviceRequest implements Callback<Integer> {

    private DeleteDeviceCallback callback;

    public void deleteDevice(String token, String deviceId, DeleteDeviceCallback callback) {
        this.callback = callback;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<Integer> call = client.deleteDeviceForUser(token, deviceId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Integer> call, Response<Integer> response) {
        if (response.isSuccessful() && response.body() != null) {
            callback.onDelete(response.body());
        }
    }

    @Override
    public void onFailure(Call<Integer> call, Throwable t) {

    }
}
