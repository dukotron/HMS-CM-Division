package com.example.homie.repositories;

import com.example.homie.network.APIConnection;
import com.example.homie.network.NetworkConnection;
import com.example.homie.viewModels.SensorDataCallBack;

import java.util.Date;

public class SensorRepository {

    private NetworkConnection connection;
    private static SensorRepository instance;

    private SensorRepository() {
        connection = new APIConnection();
    }

    public static SensorRepository getInstance() {
        if (instance == null) {
            instance = new SensorRepository();
        }
        return instance;
    }

    public void getMovementData(SensorDataCallBack viewModel, Date dateFrom, Date dateTo) {
        connection.getMovementData(viewModel, dateFrom, dateTo);
    }

    public void getCo2Data(SensorDataCallBack viewModel,Date dateFrom, Date dateTo) {
        connection.getCo2(viewModel, dateFrom, dateTo);
    }

    public void getTemperatureData(SensorDataCallBack viewModel, Date dateFrom, Date dateTo) {
        connection.getTemperatureData(viewModel, dateFrom, dateTo);
    }

    public void getHumidityData(SensorDataCallBack viewModel, Date dateFrom, Date dateTo) {
        connection.getHumidityData(viewModel, dateFrom, dateTo);
    }

    public void getLightData(SensorDataCallBack viewModel, Date dateFrom, Date dateTo) {
        connection.getLightData(viewModel, dateFrom, dateTo);
    }
}
