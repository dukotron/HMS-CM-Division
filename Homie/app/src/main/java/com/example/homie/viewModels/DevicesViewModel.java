package com.example.homie.viewModels;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homie.DRO.DeviceData;
import com.example.homie.DRO.DevicesListDRO;
import com.example.homie.DRO.Sensor;
import com.example.homie.models.CurrentData;
import com.example.homie.models.Device;
import com.example.homie.repositories.UserRepository;
import com.example.homie.viewModels.util.StatusCode;

import java.util.ArrayList;
import java.util.List;

public class DevicesViewModel extends AndroidViewModel implements DevicesCallback, DeleteDeviceCallback {

    private MutableLiveData<List<Device>> devices;
    private MutableLiveData<String> showError;
    private UserRepository userRepository;

    private List<DeviceData> devicesDRO;

    public DevicesViewModel(@NonNull Application application) {
        super(application);

        userRepository = UserRepository.getInstance();
        showError = new MutableLiveData<>();
        devices = new MutableLiveData<>();
    }

    public void loadAllDevices() {
        userRepository.getUserDevices(this);
    }

    public void deleteDevice(String deviceId) {
        userRepository.deleteDevice(deviceId, this);
    }

    @Override
    public void onReturn(DevicesListDRO userDevices) {
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

    public LiveData<String> getShowError() {
        return showError;
    }

    public LiveData<List<Device>> getDevices() {
        return devices;
    }
}
