package com.example.homie.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
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

public class DevicesViewModel extends AndroidViewModel implements DevicesCallback {

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

    @Override
    public void onReturn(DevicesListDRO userDevices) {
        if (userDevices.getStatusCode() == StatusCode.OK) {
            if (userDevices.getDevices() != null) {
                showError.setValue("You don't have devices");
                return;
            }
            devicesDRO = userDevices.getDevices();
            processDevices();
        }
    }

    private void processDevices() {
        List<Device> devicesTemp = new ArrayList<>(devicesDRO.size() + 1);
        CurrentData data;
        ArrayList<CurrentData> a = new ArrayList<>();
        for (int i = 0; i < devicesDRO.size(); i++) {
            DeviceData deviceData = devicesDRO.get(i);
            List<Sensor> sensors = deviceData.getSensors();
            for (int j = 0; j < sensors.size(); j++) {
                data = new CurrentData(sensors.get(i).getType(),
                        "" + sensors.get(i).getData().get(0).getValue());
                a.add(data);
            }
            devicesTemp.add(new Device(deviceData.getLocation(), deviceData.getId(), a));
        }
        devices.setValue(devicesTemp);
        /*
        devices = new ArrayList<>();
        CurrentData data = new CurrentData("CO2", "322");
        ArrayList<CurrentData> a = new ArrayList<>();
        a.add(data);
        data = new CurrentData("Temperature", "22");
        a.add(data);
        Device device = new Device("hallway", a);
        devices.add(device);
        data = new CurrentData("Temperature", "22");
        a = new ArrayList<>();
        a.add(data);
        device = new Device("living", a);
        devices.add(device);
        data = new CurrentData("CO2", "122");
        a = new ArrayList<>();
        a.add(data);
        device = new Device("bedroom", a);
        devices.add(device);
        */
    }

    public LiveData<String> getShowError() {
        return showError;
    }

    public LiveData<List<Device>> getDevices() {
        return devices;
    }
}
