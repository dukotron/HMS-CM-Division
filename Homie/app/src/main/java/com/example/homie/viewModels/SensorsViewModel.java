package com.example.homie.viewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.DRO.SensorData;
import com.example.homie.models.Sensor;
import com.example.homie.repositories.SensorRepository;
import com.example.homie.viewModels.util.StatusCode;

import java.util.List;

public class SensorsViewModel extends AndroidViewModel implements SensorDataCallBack {

    private SensorRepository sensorRepository;
    private MutableLiveData<List<SensorData>> movementData;
    private MutableLiveData<List<SensorData>> co2Data;

    public SensorsViewModel(@NonNull Application application) {
        super(application);
        sensorRepository = SensorRepository.getInstance();
        movementData = new MutableLiveData<>();
        co2Data = new MutableLiveData<>();
    }

    public LiveData<List<SensorData>> getMovementData() {
        return movementData;
    }

    public LiveData<List<SensorData>> getCo2Data() {
        return co2Data;
    }

    public void loadMovementData(){
        sensorRepository.getMovementData(this);
    }

    public void loadCo2Data() {
        sensorRepository.getCo2Data(this);
    }


    @Override
    public void onReturnMovementData(SensorDRO response) {
        if(response.getStatusCode() == StatusCode.OK){
            movementData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnCo2Data(SensorDRO response) {
        if(response.getStatusCode() == StatusCode.OK){
            co2Data.setValue(response.getSensorDataList());
        }
    }
}
