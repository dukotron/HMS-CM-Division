package com.example.homie.network;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.homie.DTO.AddDeviceDTO;
import com.example.homie.DTO.UserRegisterDTO;
import com.example.homie.network.retrofit.AddDeviceRequest;
import com.example.homie.network.retrofit.CO2Request;
import com.example.homie.network.retrofit.DeleteDeviceRequest;
import com.example.homie.network.retrofit.DevicesRequest;
import com.example.homie.network.retrofit.HumidityRequest;
import com.example.homie.network.retrofit.LightRequest;
import com.example.homie.network.retrofit.MovementRequest;
import com.example.homie.network.retrofit.NotificationRequest;
import com.example.homie.network.retrofit.RegisterRequest;
import com.example.homie.network.retrofit.SettingsRequest;
import com.example.homie.network.retrofit.TemperatureRequest;
import com.example.homie.viewModels.AddDeviceCallback;
import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.DeleteDeviceCallback;
import com.example.homie.viewModels.DevicesCallback;
import com.example.homie.viewModels.SensorDataCallBack;
import com.example.homie.network.util.DateFormatConverter;
import com.example.homie.viewModels.util.StatusCode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Date;

public class APIConnection implements NetworkConnection, NotificationsService {

    private FirebaseAuth auth;
    private static APIConnection instance;

    private APIConnection() {
        auth = FirebaseAuth.getInstance();
    }

    public static APIConnection getInstance() {
        if (instance == null) {
            instance = new APIConnection();
        }
        return instance;
    }

    @Override
    public void loginAccount(String email, String password, final AuthCallBack callBack) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    callBack.onReturn(StatusCode.OK);
                    Log.d("TOKEN", "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken());
                }
            }
        });
    }

    @Override
    public void logoutAccount() {
        auth.signOut();
    }

    @Override
    public void createAccount(final String firstName, final String lastName, final String email, final String password, final AuthCallBack callBack) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = auth.getCurrentUser().getUid();
                    new RegisterRequest().start(new UserRegisterDTO(userId), callBack);
                    updateNotificationToken();
                }
            }
        });
    }

    @Override
    public void getUserDevices(DevicesCallback callback) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new DevicesRequest().getAllDevices(token, userId, callback);
    }

    @Override
    public void addDevice(String deviceLocation, String deviceId, AddDeviceCallback callback) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new AddDeviceRequest().addDevice(token, new AddDeviceDTO(deviceId, userId, deviceLocation), callback);
    }

    @Override
    public void deleteDevice(String deviceId, DeleteDeviceCallback callback) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        new DeleteDeviceRequest().deleteDevice(token, deviceId, callback);
    }

    @Override
    public void getMovementData(SensorDataCallBack callBack, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        //JUST FOR TESTING
//        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjM4MDEwNjVlNGI1NjNhZWVlZWIzNTkwOTEwZDlmOTc3YTgxMjMwOWEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vaG9taWUtYjAxYzYiLCJhdWQiOiJob21pZS1iMDFjNiIsImF1dGhfdGltZSI6MTU1NzEyNjI4MCwidXNlcl9pZCI6ImJFVTBsdHkxNGJldmZrRm9pWFVJZkNHQW9KMzIiLCJzdWIiOiJiRVUwbHR5MTRiZXZma0ZvaVhVSWZDR0FvSjMyIiwiaWF0IjoxNTU3MTI2MjgwLCJleHAiOjE1NTcxMjk4ODAsImVtYWlsIjoidGVzdEB0ZXN0LnRlc3QiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsidGVzdEB0ZXN0LnRlc3QiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.B1UqGjW4ycV7WQIuojkLGHxVZ_UqwDhdv2YTT4RV5aFuwBGWRjKslme8GpvXzHL48wkDvBF7HuzaBlj46fXXIbYc3A4oWgEk4fR6aFBd5eK74uJlSU-FgDVsWoXJeB7dzrmlWWxAA2qPQaj90MM8ggxQikin9yTpHR6al9qRAAMOtsQ7jl3-bm9Zc97FghQVcpGw9LQ911daR_8siV40NdXR8dZTS2V3keoNtZgsnfiSrJqcAx6zdEfGUI5y6AYUj8uf37xXMSm9-c3A3gZUFdUAVBsa9N3aesIqi_Bda4DpnA1heJHYu9I9rFkWY70qqjdQjowqLP0rLEnam-GiAQ";
//        String userId = "bEU0lty14bevfkFoiXUIfCGAoJ32";
//        String from = "2017-09-08T19:01:55.714942";
//        String to = "2019-09-08T19:01:55.714942";
        new MovementRequest().getDailyData(token, userId, callBack, from, to);
        new MovementRequest().getHourlyData(token, userId, callBack, from, to);
    }

    @Override
    public void getCo2(SensorDataCallBack callBack, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new CO2Request().getDailyData(token, userId, callBack, from, to);
        new CO2Request().getHourlyData(token, userId, callBack, from, to);
    }

    @Override
    public void getTemperatureData(SensorDataCallBack callBack, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new TemperatureRequest().getDailyData(token, userId, callBack, from, to);
        new TemperatureRequest().getHourlyData(token, userId, callBack, from, to);
    }

    @Override
    public void updateNotificationToken() {
        final String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        final String userId = auth.getCurrentUser().getUid();
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String notificationToken = instanceIdResult.getToken();
                Log.d("```````````````````", "" + notificationToken);
                new NotificationRequest().setNotificationToken(token, userId, notificationToken);
            }
        });
    }

    @Override
    public void getHumidityData(SensorDataCallBack callBack, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new HumidityRequest().getDailyData(token, userId, callBack, from, to);
        new HumidityRequest().getHourlyData(token, userId, callBack, from, to);
    }

    @Override
    public void getLightData(SensorDataCallBack callBack, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new LightRequest().getDailyData(token, userId, callBack, from, to);
        new LightRequest().getHourlyData(token, userId, callBack, from, to);
    }

    @Override
    public void setAtHome(boolean atHome) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new SettingsRequest().setAtHome(token, userId, atHome);
    }
}
