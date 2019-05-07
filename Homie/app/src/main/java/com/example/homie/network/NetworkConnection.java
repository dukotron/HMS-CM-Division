package com.example.homie.network;

import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.SensorDataCallBack;

public interface NetworkConnection {

    void loginAccount(String email, String password, AuthCallBack callBack);
    void createAccount(String firstName, String lastName, String email, String passport, AuthCallBack callBack);
    void getMovementData(SensorDataCallBack callBack);
    void getCo2(SensorDataCallBack callBack);
    void getTemperatureData(SensorDataCallBack callBack);
    void getHumidityData(SensorDataCallBack callBack);
}
