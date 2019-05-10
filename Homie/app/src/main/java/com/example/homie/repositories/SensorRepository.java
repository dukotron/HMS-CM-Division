package com.example.homie.repositories;

import com.example.homie.network.APIConnection;
import com.example.homie.network.NetworkConnection;
import com.example.homie.viewModels.SensorDataCallBack;

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

    public void getMovementData(SensorDataCallBack viewModel) {
        connection.getMovementData( viewModel);
    }

    public void getCo2Data(SensorDataCallBack viewModel) {
        connection.getCo2(viewModel);
    }

    public void getTemperatureData(SensorDataCallBack viewModel) {
        connection.getTemperatureData(viewModel);
    }

    public void getHumidityData(SensorDataCallBack viewModel) {
        connection.getHumidityData(viewModel);
    }
}
