package com.example.homie.network.retrofit;

import android.util.Log;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.DTO.UserLoginDTO;
import com.example.homie.viewModel.AuthCallBack;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.homie.network.util.NetworkConfig.BASE_URL;

public class LoginRequest implements LoginCallback {

    private final static String TAG = "Login Request";
    private AuthCallBack callBack;

    @Override
    public void start(UserLoginDTO user, AuthCallBack callBack) {
        Log.d(TAG, "Login request started");
        this.callBack = callBack;
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        RetrofitAPI server = retrofit.create(RetrofitAPI.class);
        Call<AuthDRO> call = server.loginAccount(user);
        call.enqueue(this);

    }

    @Override
    public void onResponse(Call<AuthDRO> call, Response<AuthDRO> response) {
        if(response.code() == 200){
            Log.d("Callback info"," status code " + response.body()+" ```````````````````````````````````````` ");
            //callBack.onReturn(true);
        }else {
            Log.d("Callback info", "status code" + response.body());
        }
    }

    @Override
    public void onFailure(Call<AuthDRO> call, Throwable t) {
        Log.d("Callback info","``````````````````````````````ERROOROOOROOOOROOOOR``````````");
    }


}
