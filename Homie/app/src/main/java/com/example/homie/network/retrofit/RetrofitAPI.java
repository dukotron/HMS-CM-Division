package com.example.homie.network.retrofit;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.DRO.MovementDRO;
import com.example.homie.DTO.UserLoginDTO;
import com.example.homie.DTO.UserRegisterDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {


    @POST("api/user/register")
    Call<AuthDRO> createAccount(@Body UserRegisterDTO user);

    @POST("api/user/login")
    Call<AuthDRO> loginAccount(@Body UserLoginDTO user);

    @GET("api/movement/dailyavg")
    Call<List<MovementDRO>> getMovementData(@Query("userId") int userId);


}

