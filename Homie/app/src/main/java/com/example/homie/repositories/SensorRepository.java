package com.example.homie.repositories;

import com.example.homie.network.APIConnection;
import com.example.homie.network.NetworkConnection;
import com.example.homie.viewModels.DevicesCallback;
import com.example.homie.viewModels.SensorDataCallBack;

import java.util.Date;

public class SensorRepository {

    private NetworkConnection connection;
    private static SensorRepository instance;

    private SensorRepository() {
        connection = APIConnection.getInstance();
    }

    public static SensorRepository getInstance() {
        if (instance == null) {
            instance = new SensorRepository();
        }
        return instance;
    }

    public void getHomeScore(DevicesCallback callback, Date dateFrom, Date dateTo) {
        connection.getUserHomeScore(callback, dateFrom, dateTo);
    }

    public void getMovementData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        connection.getMovementData(callBack, deviceId, dateFrom, dateTo);
    }

    public void getCo2Data(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        connection.getCo2(callBack, deviceId, dateFrom, dateTo);
    }

    public void getTemperatureData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        connection.getTemperatureData(callBack, deviceId, dateFrom, dateTo);
    }

    public void getHumidityData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        connection.getHumidityData(callBack, deviceId, dateFrom, dateTo);
    }

    public void getLightData(SensorDataCallBack callBack, String deviceId, Date dateFrom, Date dateTo) {
        connection.getLightData(callBack, deviceId, dateFrom, dateTo);
    }
}
