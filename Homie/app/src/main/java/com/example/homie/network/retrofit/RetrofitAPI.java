package com.example.homie.network.retrofit;

import com.example.homie.DRO.DevicesListDRO;
import com.example.homie.DRO.SensorDRO;
import com.example.homie.DTO.AddDeviceDTO;
import com.example.homie.DTO.UserRegisterDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface RetrofitAPI {


    @POST("api/user/register")
    Call<Integer> createAccount(@Body UserRegisterDTO user);

    @PUT("api/device/updateOwnerAndLoc")
    Call<Integer> addDeviceForUser(@Header("Authorization") String token, @Body AddDeviceDTO deviceInfo);

    @DELETE("api/device/deleteOwnerAndLoc")
    Call<Integer> deleteDeviceForUser(@Header("Authorization") String token, @Query("deviceId") String deviceId);

    @GET("api/device/getUserDevices")
    Call<DevicesListDRO> getUserDevices(@Header("Authorization") String token, @Query("userId") String userId);

    @GET("api/movement/dailyavg")
    Call<SensorDRO> getMovementData(@Header("Authorization") String token, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/co/???")
    Call<SensorDRO> getCo2Data(@Header("Authorization") String token, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/temperature/???")
    Call<SensorDRO> getTemperatureData(@Header("Authorization") String token, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/humidity/???")
    Call<SensorDRO> getHumidityData(@Header("Authorization") String token, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @POST("api/preferences/atHome")
    Call<String> setAtHome(@Header("Authorization") String token, @Query("userId") String userId, @Body boolean atHome);
    
    @PUT("api/user/notificationToken")
    Call<Integer> setNotificationToken(@Header("Authorization") String tokenAuth, @Query("userId") String userId, @Query("token") String notificationToken);
}

