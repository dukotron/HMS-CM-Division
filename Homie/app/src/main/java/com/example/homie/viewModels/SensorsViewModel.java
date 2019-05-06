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
    private MutableLiveData<List<SensorData>> temperatureData;
    private MutableLiveData<List<SensorData>> humidityData;

    public SensorsViewModel(@NonNull Application application) {
        super(application);
        sensorRepository = SensorRepository.getInstance();
        movementData = new MutableLiveData<>();
        co2Data = new MutableLiveData<>();
        temperatureData = new MutableLiveData<>();
        humidityData = new MutableLiveData<>();
    }

    public LiveData<List<SensorData>> getMovementData() {
        return movementData;
    }

    public LiveData<List<SensorData>> getCoData() {
        return co2Data;
    }

    public LiveData<List<SensorData>> getHumidityData() {
        return humidityData;
    }

    public LiveData<List<SensorData>> getTemperatureData() {
        return temperatureData;
    }

    public void loadMovementData(){
        sensorRepository.getMovementData(this);
    }

    public void loadCoData() {
        sensorRepository.getCo2Data(this);
    }

    public void loadTemperatureData() {sensorRepository.getTemperatureData(this);}

    public void loadHumidityData() { sensorRepository.getHumidityData(this);}


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

    @Override
    public void onReturnHumidityData(SensorDRO response) {
        if(response.getStatusCode() == StatusCode.OK){
            humidityData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnTemperatureData(SensorDRO response) {
        if(response.getStatusCode() == StatusCode.OK){
            temperatureData.setValue(response.getSensorDataList());
        }
    }
}
