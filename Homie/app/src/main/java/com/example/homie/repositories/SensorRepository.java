package com.example.homie.repositories;

import com.example.homie.network.APIConnection;
import com.example.homie.network.NetworkConnection;
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

    public void getMovementData(SensorDataCallBack viewModel, String deviceId, Date dateFrom, Date dateTo) {
        connection.getMovementData(viewModel, deviceId, dateFrom, dateTo);
    }

    public void getCo2Data(SensorDataCallBack viewModel, String deviceId, Date dateFrom, Date dateTo) {
        connection.getCo2(viewModel, deviceId, dateFrom, dateTo);
    }

    public void getTemperatureData(SensorDataCallBack viewModel, String deviceId, Date dateFrom, Date dateTo) {
        connection.getTemperatureData(viewModel, deviceId, dateFrom, dateTo);
    }

    public void getHumidityData(SensorDataCallBack viewModel, String deviceId, Date dateFrom, Date dateTo) {
        connection.getHumidityData(viewModel, deviceId, dateFrom, dateTo);
    }

    public void getLightData(SensorDataCallBack viewModel, String deviceId, Date dateFrom, Date dateTo) {
        connection.getLightData(viewModel, deviceId, dateFrom, dateTo);
    }
}
