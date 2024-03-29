package com.example.homie.network;

import com.example.homie.viewModels.AddDeviceCallback;
import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.DevicesCallback;
import com.example.homie.viewModels.SensorDataCallBack;

import java.util.Date;

public interface NetworkConnection {

    void loginAccount(String email, String password, AuthCallBack callBack);

    void logoutAccount();

    void createAccount(String email, String passport, AuthCallBack callBack);

    void setAtHome(boolean atHome);

    void getUserDevices(DevicesCallback callback);

    void getUserHomeScore(DevicesCallback callBack, Date dateFrom, Date dateTo);

    void addDevice(String deviceLocation, String deviceId, AddDeviceCallback callback);

    void deleteDevice(String deviceId, DevicesCallback callback);

    void getMovementData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo);

    void getCo2(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo);

    void getTemperatureData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo);

    void getHumidityData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo);

    void getLightData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo);
}
