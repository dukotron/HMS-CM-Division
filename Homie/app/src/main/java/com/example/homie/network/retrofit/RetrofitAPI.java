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

    @GET("api/score/hourlyscore")
    Call<SensorDRO> getHourlyScore(@Header("Authorization") String token, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/movement/dailyavg")
    Call<SensorDRO> getMovementDailyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/movement/hourlyavg")
    Call<SensorDRO> getMovementHourlyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/co2/dailyavg")
    Call<SensorDRO> getCo2DailyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/co2/hourlyavg")
    Call<SensorDRO> getCo2HourlyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/Temperature/dailyavg")
    Call<SensorDRO> getTemperatureDailyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/Temperature/hourlyavg")
    Call<SensorDRO> getTemperatureHourlyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/Humidity/dailyavg")
    Call<SensorDRO> getHumidityDailyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/Humidity/hourlyavg")
    Call<SensorDRO> getHumidityHourlyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/Light/dailyavg")
    Call<SensorDRO> getLightDailyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @GET("api/Light/hourlyavg")
    Call<SensorDRO> getLightHourlyData(@Header("Authorization") String token, @Query("deviceId") String deviceId, @Query("userId") String userId, @Query("from") String dateFrom, @Query("to") String dateTo);

    @POST("api/preferences/atHome")
    Call<String> setAtHome(@Header("Authorization") String token, @Query("userId") String userId, @Body boolean atHome);

    @PUT("api/user/notificationToken")
    Call<Integer> setNotificationToken(@Header("Authorization") String tokenAuth, @Query("userId") String userId, @Query("token") String notificationToken);
}

