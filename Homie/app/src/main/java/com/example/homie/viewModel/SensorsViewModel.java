package com.example.homie.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.DRO.MovementDRO;
import com.example.homie.DRO.SensorData;
import com.example.homie.repository.SensorRepository;
import com.example.homie.viewModel.util.TempMemory;

import java.util.ArrayList;
import java.util.List;

public class SensorsViewModel extends AndroidViewModel implements MovementSensorCallBack{

    private SensorRepository sensorRepository;
    private List<SensorData> movementData;

    public SensorsViewModel(@NonNull Application application) {
        super(application);
        sensorRepository = SensorRepository.getInstance();
        movementData = new ArrayList<>();
    }

    public void getMovementData() {
        sensorRepository.getMovementData(Integer.valueOf(TempMemory.getUserId(getApplication().getApplicationContext())),this);
    }

    @Override
    public void onReturn(MovementDRO response) {
        movementData = response.getSensorDataList();
    }


}
