package com.example.homie.network;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.homie.DTO.AddDeviceDTO;
import com.example.homie.DTO.UserRegisterDTO;
import com.example.homie.network.retrofit.AddDeviceRequest;
import com.example.homie.network.retrofit.CO2Request;
import com.example.homie.network.retrofit.DevicesRequest;
import com.example.homie.network.retrofit.HumidityRequest;
import com.example.homie.network.retrofit.LightRequest;
import com.example.homie.network.retrofit.MovementRequest;
import com.example.homie.network.retrofit.NotificationRequest;
import com.example.homie.network.retrofit.RegisterRequest;
import com.example.homie.network.retrofit.ScoreRequest;
import com.example.homie.network.retrofit.SettingsRequest;
import com.example.homie.network.retrofit.TemperatureRequest;
import com.example.homie.viewModels.AddDeviceCallback;
import com.example.homie.viewModels.AuthCallBack;
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
                    updateNotificationToken();
                }
            }
        });
    }

    @Override
    public void logoutAccount() {
        auth.signOut();
    }

    @Override
    public void createAccount(final String email, final String password, final AuthCallBack callBack) {
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
    public void getUserHomeScore(DevicesCallback callBack, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new ScoreRequest().getHourlyScore(token, userId, callBack, from, to);
    }

    @Override
    public void addDevice(String deviceLocation, String deviceId, AddDeviceCallback callback) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new AddDeviceRequest().addDevice(token, new AddDeviceDTO(deviceId, userId, deviceLocation), callback);
    }

    @Override
    public void deleteDevice(String deviceId, DevicesCallback callback) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        new DevicesRequest().deleteDevice(token, deviceId, callback);
    }

    @Override
    public void getMovementData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new MovementRequest().getDailyData(token, deviceId, userId, callBack, from, to);
        new MovementRequest().getHourlyData(token, deviceId, userId, callBack, from, to);
    }

    @Override
    public void getCo2(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new CO2Request().getDailyData(token, deviceId, userId, callBack, from, to);
        new CO2Request().getHourlyData(token, deviceId, userId, callBack, from, to);
    }

    @Override
    public void getTemperatureData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new TemperatureRequest().getDailyData(token, deviceId, userId, callBack, from, to);
        new TemperatureRequest().getHourlyData(token, deviceId, userId, callBack, from, to);
    }

    @Override
    public void updateNotificationToken() {
        final String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        final String userId = auth.getCurrentUser().getUid();
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String notificationToken = instanceIdResult.getToken();
                new NotificationRequest().setNotificationToken(token, userId, notificationToken);
            }
        });
    }

    @Override
    public void getHumidityData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new HumidityRequest().getDailyData(token, deviceId, userId, callBack, from, to);
        new HumidityRequest().getHourlyData(token, deviceId, userId, callBack, from, to);
    }

    @Override
    public void getLightData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new LightRequest().getDailyData(token, deviceId, userId, callBack, from, to);
        new LightRequest().getHourlyData(token, deviceId, userId, callBack, from, to);
    }

    @Override
    public void setAtHome(boolean atHome) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new SettingsRequest().setAtHome(token, userId, atHome);
    }
}
