package com.example.homie.network.retrofit;

import android.util.Log;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.DTO.UserRegisterDTO;
import com.example.homie.viewModel.AuthCallBack;
import com.example.homie.viewModel.util.StatusCode;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class RegisterRequest implements RegisterCallback {

    private AuthCallBack callBack;

    @Override
    public void start(UserRegisterDTO user, AuthCallBack callBack) {
        this.callBack = callBack;

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI client = retrofit.create(RetrofitAPI.class);
        Call<AuthDRO> call = client.createAccount(user);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<AuthDRO> call, Response<AuthDRO> response) {
        if(response.code() == 200){
            callBack.onReturn(response.body());
        }else {
           callBack.onReturn(new AuthDRO(StatusCode.NOT_OK));
        }
    }

    @Override
    public void onFailure(Call<AuthDRO> call, Throwable t) {
        callBack.onReturn(new AuthDRO(StatusCode.NOT_OK));
    }
}
