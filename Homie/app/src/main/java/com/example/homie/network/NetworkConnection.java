package com.example.homie.network;

import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.SensorDataCallBack;

import java.util.Date;

public interface NetworkConnection {

    void loginAccount(String email, String password, AuthCallBack callBack);
    void createAccount(String firstName, String lastName, String email, String passport, AuthCallBack callBack);
    void setAtHome(boolean atHome);
    void getMovementData(SensorDataCallBack callBack, Date dateFrom, Date dateTo);
    void getCo2(SensorDataCallBack callBack, Date dateFrom, Date dateTo);
    void getTemperatureData(SensorDataCallBack callBack, Date dateFrom, Date dateTo);
    void getHumidityData(SensorDataCallBack callBack, Date dateFrom, Date dateTo);
    void getLightData(SensorDataCallBack callBack, Date dateFrom, Date dateTo);
}
