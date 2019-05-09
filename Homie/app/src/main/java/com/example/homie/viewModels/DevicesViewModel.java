package com.example.homie.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.homie.DRO.DevicesListDRO;
import com.example.homie.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class DevicesViewModel extends AndroidViewModel implements DevicesCallback{

    private UserRepository userRepository;

    public DevicesViewModel(@NonNull Application application) {
        super(application);

        userRepository = UserRepository.getInstance();
    }

    public List<?> getAllDevices(){
        userRepository.getUserDevices(this);
        return new ArrayList<>();
    }

    @Override
    public void onReturn(DevicesListDRO devices) {

    }
}
