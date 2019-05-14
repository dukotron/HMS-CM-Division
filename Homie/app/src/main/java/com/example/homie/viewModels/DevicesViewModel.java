package com.example.homie.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homie.DRO.DeviceData;
import com.example.homie.DRO.DevicesListDRO;
import com.example.homie.DRO.Sensor;
import com.example.homie.DRO.SensorDRO;
import com.example.homie.models.CurrentData;
import com.example.homie.models.Device;
import com.example.homie.repositories.SensorRepository;
import com.example.homie.repositories.UserRepository;
import com.example.homie.viewModels.util.StatusCode;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DevicesViewModel extends AndroidViewModel implements DevicesCallback {

    private MutableLiveData<List<Device>> devices;
    private MutableLiveData<String> showError;
    private MutableLiveData<Float> score;
    private UserRepository userRepository;
    private SensorRepository sensorRepository;

    private List<DeviceData> devicesDRO;

    public DevicesViewModel(@NonNull Application application) {
        super(application);

        userRepository = UserRepository.getInstance();
        sensorRepository = SensorRepository.getInstance();
        showError = new MutableLiveData<>();
        devices = new MutableLiveData<>();
        score = new MutableLiveData<>();
    }

    public void loadAllDevices() {
        userRepository.getUserDevices(this);
    }

    public void loadHomeScore() {
        Calendar cal = Calendar.getInstance();
        Date todayDate = cal.getTime();
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)-1,0,0);
        Date yesterdayDate = cal.getTime();
        sensorRepository.getHomeScore(this, yesterdayDate, todayDate);
    }

    public void deleteDevice(String deviceId) {
        userRepository.deleteDevice(deviceId, this);
    }

    @Override
    public void onReturnAllDevices(DevicesListDRO userDevices) {
        if (userDevices.getStatusCode() == StatusCode.OK) {
            if (userDevices.getDevices() == null) {
                showError.setValue("You don't have devices");
                return;
            }
            devicesDRO = userDevices.getDevices();
            processDevices();
        }
    }

    @Override
    public void onDelete(int statusCode) {
        if (statusCode == StatusCode.OK) {
            loadAllDevices();
            showError.setValue("Device was deleted");
        } else {
            showError.setValue("Device wasn't deleted");
        }
    }

    @Override
    public void onReturnScore(SensorDRO scoreData) {
        if (scoreData.getStatusCode() == StatusCode.OK) {
            score.setValue(scoreData.getSensorDataList().get(0).getValue());
        }
    }

    private void processDevices() {

        List<Device> devicesTemp = new ArrayList<>(devicesDRO.size() + 1);
        CurrentData data;
        for (int i = 0; i < devicesDRO.size(); i++) {
            DeviceData deviceData = devicesDRO.get(i);
            List<Sensor> sensors = deviceData.getSensors();
            ArrayList<CurrentData> a = new ArrayList<>();
            for (int j = 0; j < sensors.size(); j++) {
                data = new CurrentData(sensors.get(j).getType(),
                        "" + sensors.get(j).getData().get(0).getValue());
                a.add(data);
            }
            devicesTemp.add(new Device(deviceData.getLocation(), deviceData.getId(), a));
        }
        devices.setValue(devicesTemp);
    }

    public LiveData<Float> getScore() {
        return score;
    }

    public LiveData<String> getShowError() {
        return showError;
    }

    public LiveData<List<Device>> getDevices() {
        return devices;
    }
}
