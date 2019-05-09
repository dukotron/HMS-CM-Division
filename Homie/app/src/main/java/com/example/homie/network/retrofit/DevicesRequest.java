package com.example.homie.network.retrofit;

import com.example.homie.DRO.DevicesListDRO;
import com.example.homie.viewModels.DevicesCallback;

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

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<DevicesListDRO> call = client.getUserDevices(token, userId);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<DevicesListDRO> call, Response<DevicesListDRO> response) {

    }

    @Override
    public void onFailure(Call<DevicesListDRO> call, Throwable t) {

    }
}
