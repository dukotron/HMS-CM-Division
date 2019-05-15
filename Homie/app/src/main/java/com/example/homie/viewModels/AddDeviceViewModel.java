package com.example.homie.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.homie.repositories.UserRepository;
import com.example.homie.viewModels.util.InputDataValidator;
import com.example.homie.viewModels.util.StatusCode;

public class AddDeviceViewModel extends AndroidViewModel implements AddDeviceCallback {

    private MutableLiveData<Boolean> isAdded;
    private MutableLiveData<Boolean> isValidLocation;
    private MutableLiveData<Boolean> isValidId;
    private MutableLiveData<Boolean> showError;

    private UserRepository userRepository;

    public AddDeviceViewModel(@NonNull Application application) {
        super(application);

        userRepository = UserRepository.getInstance();

        isAdded = new MutableLiveData<>();
        isValidId = new MutableLiveData<>();
        isValidLocation = new MutableLiveData<>();
        showError = new MutableLiveData<>();

        isAdded.setValue(false);
        isValidLocation.setValue(true);
        isValidId.setValue(true);
        showError.setValue(false);
    }

    public void addDevice(String deviceLocation, String deviceId) {
        if (checkEnteredData(deviceLocation, deviceId)) {
            userRepository.addDevice(deviceLocation, deviceId, this);
        }
    }

    @Override
    public void onReturn(int statusCode) {
        if (statusCode == StatusCode.OK) {
            isAdded.setValue(true);
        } else {
            showError.setValue(true);
        }
    }

    private boolean checkEnteredData(String deviceLocation, String deviceId) {
        boolean valid = true;
        if (deviceLocation.isEmpty()) {
            isValidLocation.setValue(false);
            valid = false;
        }
        if (deviceId.isEmpty()) {
            isValidId.setValue(false);
            valid = false;
        }
        return valid;
    }

    public LiveData<Boolean> getIsAdded() {
        return isAdded;
    }

    public LiveData<Boolean> getIsValidLocation() {
        return isValidLocation;
    }

    public LiveData<Boolean> getIsValidId() {
        return isValidId;
    }

    public MutableLiveData<Boolean> getShowError() {
        return showError;
    }
}
