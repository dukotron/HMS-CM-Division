package com.example.homie.network.retrofit;

import android.util.Log;

import com.example.homie.network.DRO.AuthDRO;
import com.example.homie.network.DTO.UserLoginDTO;
import com.example.homie.network.DTO.UserRegisterDTO;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class LoginRequest implements LoginCallback {

    private final static String TAG = "Login Request";

    @Override
    public AuthDRO start(UserLoginDTO user) {
        Log.d(TAG, "Login request started");

        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<UserLoginDTO> call = client.loginAccount(user);
        call.enqueue(this);

        return null;
       // return new AuthDRO();
    }

    @Override
    public void onResponse(Call<UserLoginDTO> call, Response<UserLoginDTO> response) {

    }

    @Override
    public void onFailure(Call<UserLoginDTO> call, Throwable t) {

    }


}
