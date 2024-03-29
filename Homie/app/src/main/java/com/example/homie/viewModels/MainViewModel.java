package com.example.homie.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.homie.repositories.UserRepository;

public class MainViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isLoggedOut;

    private UserRepository userRepository;

    public MainViewModel(@NonNull Application application) {
        super(application);

        userRepository = UserRepository.getInstance();

        isLoggedOut = new MutableLiveData<>();
        isLoggedOut.setValue(false);

    }

    public void logoutUser() {
        userRepository.logoutAccount();
        isLoggedOut.setValue(true);
    }

    public LiveData<Boolean> getIsLoggedOut() {
        return isLoggedOut;
    }
}
