package com.example.homie.network.retrofit;

import android.util.Log;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.DTO.UserLoginDTO;
import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.util.StatusCode;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class LoginRequest implements LoginCallback {

    private AuthCallBack callBack;

    @Override
    public void start(UserLoginDTO user, AuthCallBack callBack) {
        this.callBack = callBack;
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitAPI server = retrofit.create(RetrofitAPI.class);
        Call<AuthDRO> call = server.loginAccount(user);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<AuthDRO> call, Response<AuthDRO> response) {
        if(response.code() == 200){
            callBack.onReturn(response.body());
            Log.d("LoginRequest", response.body().toString());
        }else {
            callBack.onReturn(new AuthDRO(StatusCode.NOT_OK));
        }
    }

    @Override
    public void onFailure(Call<AuthDRO> call, Throwable t) {
        Log.d("LoginRequest", t.toString());
        callBack.onReturn(new AuthDRO(StatusCode.NOT_OK));
    }


}
