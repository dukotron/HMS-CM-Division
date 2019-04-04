package com.example.homie.network.retrofit;

import com.example.homie.network.DRO.AuthDRO;
import com.example.homie.network.DTO.UserLoginDTO;
import com.example.homie.network.DTO.UserRegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI {


    @POST("api/user/register")
    Call<AuthDRO> createAccount(@Body UserRegisterDTO user);


    @POST("api/user/login")
    Call<AuthDRO> loginAccount(@Body UserLoginDTO user) ;


}

