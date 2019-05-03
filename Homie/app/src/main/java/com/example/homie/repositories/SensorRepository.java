package com.example.homie.repositories;

import com.example.homie.network.APIConnection;
import com.example.homie.network.NetworkConnection;
import com.example.homie.viewModels.MovementSensorCallBack;

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

    public void getMovementData(MovementSensorCallBack viewModel) {
        connection.getMovementData( viewModel);
    }
}
