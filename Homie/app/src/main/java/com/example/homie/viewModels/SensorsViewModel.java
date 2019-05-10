package com.example.homie.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.DRO.SensorData;
import com.example.homie.repositories.SensorRepository;
import com.example.homie.viewModels.util.StatusCode;

import java.util.Date;
import java.util.List;

public class SensorsViewModel extends AndroidViewModel implements SensorDataCallBack {

    private SensorRepository sensorRepository;
    private MutableLiveData<List<SensorData>> movementDailyData;
    private MutableLiveData<List<SensorData>> movementHourlyData;
    private MutableLiveData<List<SensorData>> co2DailyData;
    private MutableLiveData<List<SensorData>> co2HourlyData;
    private MutableLiveData<List<SensorData>> temperatureDailyData;
    private MutableLiveData<List<SensorData>> temperatureHourlyData;
    private MutableLiveData<List<SensorData>> humidityDailyData;
    private MutableLiveData<List<SensorData>> humidityHourlyData;
    private MutableLiveData<List<SensorData>> lightDailyData;
    private MutableLiveData<List<SensorData>> lightHourlyData;

    public SensorsViewModel(@NonNull Application application) {
        super(application);
        sensorRepository = SensorRepository.getInstance();
        movementDailyData = new MutableLiveData<>();
        movementHourlyData = new MutableLiveData<>();
        co2DailyData = new MutableLiveData<>();
        co2HourlyData = new MutableLiveData<>();
        temperatureDailyData = new MutableLiveData<>();
        temperatureHourlyData = new MutableLiveData<>();
        humidityDailyData = new MutableLiveData<>();
        humidityHourlyData = new MutableLiveData<>();
        lightDailyData = new MutableLiveData<>();
        lightHourlyData = new MutableLiveData<>();
    }

    public LiveData<List<SensorData>> getMovementDailyData() {
        return movementDailyData;
    }

    public LiveData<List<SensorData>> getMovementHourlyData() {
        return movementHourlyData;
    }

    public LiveData<List<SensorData>> getCoDailyData() {
        return co2DailyData;
    }

    public LiveData<List<SensorData>> getCoHourlyData() {
        return co2HourlyData;
    }

    public LiveData<List<SensorData>> getHumidityDailyData() {
        return humidityDailyData;
    }

    public LiveData<List<SensorData>> getHumidityHourlyData() {
        return humidityHourlyData;
    }

    public LiveData<List<SensorData>> getTemperatureDailyData() {
        return temperatureDailyData;
    }

    public LiveData<List<SensorData>> getTemperatureHourlyData() {
        return temperatureHourlyData;
    }

    public LiveData<List<SensorData>> getLightDailyData() {
        return lightDailyData;
    }

    public LiveData<List<SensorData>> getLightHourlyData() {
        return lightHourlyData;
    }

    public void loadMovementData(Date dateFrom, Date dateTo) {
        sensorRepository.getMovementData(this, dateFrom, dateTo);
    }

    public void loadCoData(Date dateFrom, Date dateTo) {
        sensorRepository.getCo2Data(this, dateFrom, dateTo);
    }

    public void loadTemperatureData(Date dateFrom, Date dateTo) {
        sensorRepository.getTemperatureData(this, dateFrom, dateTo);
    }

    public void loadHumidityData(Date dateFrom, Date dateTo) {
        sensorRepository.getHumidityData(this, dateFrom, dateTo);
    }

    public void loadLightData(Date dateFrom, Date dateTo) {
        sensorRepository.getLightData(this, dateFrom, dateTo);
    }

    @Override
    public void onReturnMovementDailyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            movementDailyData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnMovementHourlyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            movementHourlyData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnCo2DailyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            co2DailyData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnCo2HourlyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            co2HourlyData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnHumidityDailyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            humidityDailyData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnHumidityHourlyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            humidityHourlyData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnTemperatureDailyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            temperatureDailyData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnTemperatureHourlyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            temperatureHourlyData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnLightDailyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            lightDailyData.setValue(response.getSensorDataList());
        }
    }

    @Override
    public void onReturnLightHourlyData(SensorDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            lightHourlyData.setValue(response.getSensorDataList());
        }
    }
}
