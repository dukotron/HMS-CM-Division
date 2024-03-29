package com.example.homie.viewModels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.homie.repositories.UserRepository;
import com.example.homie.viewModels.util.InputDataValidator;
import com.example.homie.viewModels.util.StatusCode;
import com.example.homie.viewModels.util.TempMemory;

public class RegisterViewModel extends AndroidViewModel implements AuthCallBack {
    private MutableLiveData<Boolean> isValidFirstName;
    private MutableLiveData<Boolean> isValidLastName;
    private MutableLiveData<Boolean> isValidEmail;
    private MutableLiveData<Boolean> isValidPassword;
    private MutableLiveData<Boolean> isRegistered;
    private MutableLiveData<Boolean> showError;

    private UserRepository userRepository;

    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();

        isValidFirstName = new MutableLiveData<>();
        isValidLastName = new MutableLiveData<>();
        isValidEmail = new MutableLiveData<>();
        isValidPassword = new MutableLiveData<>();
        isRegistered = new MutableLiveData<>();
        showError = new MutableLiveData<>();

        isValidFirstName.setValue(true);
        isValidLastName.setValue(true);
        isValidEmail.setValue(true);
        isValidPassword.setValue(true);
        isRegistered.setValue(false);
        showError.setValue(false);
    }

    public void registerUser(String firstName, String lastName, String email, String password) {
        if (checkEnteredData(firstName, lastName, email, password)) {
            TempMemory.saveUserName(getApplication().getApplicationContext(), firstName + " " + lastName);
            userRepository.createAccount(email, password, this);
        }
    }

    private boolean checkEnteredData(String firstName, String lastName, String email, String password) {
        boolean valid = true;
        if (!InputDataValidator.isStringValid(firstName)) {
            isValidFirstName.setValue(false);
            valid = false;
        }

        if (!InputDataValidator.isStringValid(lastName)) {
            isValidLastName.setValue(false);
            valid = false;
        }

        if (!InputDataValidator.isEmailValid(email)) {
            isValidEmail.setValue(false);
            valid = false;
        }

        if (!InputDataValidator.isPasswordValid(password)) {
            isValidPassword.setValue(false);
            valid = false;
        }
        return valid;
    }

    public LiveData<Boolean> getIsValidFirstName() {
        return isValidFirstName;
    }

    public LiveData<Boolean> getIsValidLastName() {
        return isValidLastName;
    }

    public LiveData<Boolean> getIsValidEmail() {
        return isValidEmail;
    }

    public LiveData<Boolean> getIsValidPassword() {
        return isValidPassword;
    }

    public LiveData<Boolean> getIsRegistered() {
        return isRegistered;
    }

    public LiveData<Boolean> getShowError() {
        return showError;
    }

    @Override
    public void onReturn(int statusCode) {
        if (statusCode == StatusCode.OK) {
            isRegistered.setValue(true);
        } else {
            showError.setValue(true);
        }
    }
}
