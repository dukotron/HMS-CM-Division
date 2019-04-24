package com.example.homie.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.homie.repository.UserRepository;
import com.example.homie.viewModel.util.TempMemory;

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
