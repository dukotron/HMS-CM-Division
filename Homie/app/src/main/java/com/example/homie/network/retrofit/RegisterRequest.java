package com.example.homie.network.retrofit;

import android.util.Log;

import com.example.homie.network.DRO.AuthDRO;
import com.example.homie.network.DTO.UserRegisterDTO;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class RegisterRequest implements RegisterCallback {


    private final static String TAG = "Register Request";


    @Override
    public void start(UserRegisterDTO user) {
        Log.d(TAG, "Register request started");

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<AuthDRO> call = client.createAccount(user);
        call.enqueue(this);


    }

    @Override
    public void onResponse(Call<AuthDRO> call, Response<AuthDRO> response) {
        Log.d("Callback info",response.body().getStatusCode()+"````````````````````````````````````````");
    }

    @Override
    public void onFailure(Call<AuthDRO> call, Throwable t) {
        Log.d("Callback info","``````````````````EROROROROROROROROROROROR``````````````````````");
    }




}
