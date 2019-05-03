package com.example.homie.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.homie.repositories.UserRepository;
import com.example.homie.viewModels.util.TempMemory;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isLoggedOut;

    private UserRepository userRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        userRepository = UserRepository.getInstance();

        isLoggedOut = new MutableLiveData<>();
        isLoggedOut.setValue(false);

    }

    public void logoutUser(){
        userRepository.logoutAccount(TempMemory.getUserId(getApplication().getApplicationContext()));
    }

    public MutableLiveData<Boolean> getIsLoggedOut() {
        return isLoggedOut;
    }
}
