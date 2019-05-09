package com.example.homie.network.retrofit;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.DTO.UserRegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RetrofitAPI {


    @POST("api/user/register")
    Call<Integer> createAccount(@Body UserRegisterDTO user);

    @GET("api/movement/dailyavg")
    Call<SensorDRO> getMovementData(@Header("Authorization") String token, @Query("userId") String userId);

    @GET("api/movement/???")
    Call<SensorDRO> getCo2Data(@Header("Authorization") String token, @Query("userId") String userId);

    @GET("api/temperature/???")
    Call<SensorDRO> getTemperatureData(@Header("Authorization") String token, @Query("userId") String userId);

    @GET("api/humidity/???")
    Call<SensorDRO> getHumidityData(@Header("Authorization") String token, @Query("userId") String userId);

    @PUT("api/user/notificationToken")
    Call<Integer> setNotificationToken(@Header("Authorization") String tokenAuth, @Query("userId") String userId, @Query("token") String tokenNotif);
}

