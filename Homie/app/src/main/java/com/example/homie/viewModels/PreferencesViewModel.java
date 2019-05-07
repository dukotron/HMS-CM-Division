package com.example.homie.viewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.homie.repositories.UserRepository;

public class PreferencesViewModel extends AndroidViewModel {

    private UserRepository userRepository;

    public PreferencesViewModel(@NonNull Application application) {
        super(application);

        userRepository = UserRepository.getInstance();
    }

    public void setAtHome(boolean atHome){
        userRepository.setAtHome(atHome);
    }
}
