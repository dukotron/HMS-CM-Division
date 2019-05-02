package com.example.homie.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.repository.UserRepository;
import com.example.homie.viewModel.util.InputDataValidator;
import com.example.homie.viewModel.util.StatusCode;
import com.example.homie.viewModel.util.TempMemory;

public class RegisterViewModel extends AndroidViewModel implements AuthCallBack {
    private MutableLiveData<Boolean> isValidFirstName;
    private MutableLiveData<Boolean> isValidLastName;
    private MutableLiveData<Boolean> isValidEmail;
    private MutableLiveData<Boolean> isValidPassword;

    private MutableLiveData<Boolean> isRegistered;

    private MutableLiveData<Boolean> showError;

    private UserRepository userRepository;

    //TODO move IP address to separate configuration class


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
            userRepository.createAccount(firstName, lastName, email, password, this);
        }
    }

    boolean checkEnteredData(String firstName, String lastName, String email, String password) {
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

    public MutableLiveData<Boolean> getIsValidFirstName() {
        return isValidFirstName;
    }

    public MutableLiveData<Boolean> getIsValidLastName() {
        return isValidLastName;
    }

    public MutableLiveData<Boolean> getIsValidEmail() {
        return isValidEmail;
    }

    public MutableLiveData<Boolean> getIsValidPassword() {
        return isValidPassword;
    }

    public MutableLiveData<Boolean> getIsRegistered() {
        return isRegistered;
    }

    public MutableLiveData<Boolean> getShowError() {
        return showError;
    }

    @Override
    public void onReturn(AuthDRO response) {
        if(response.getStatusCode() == StatusCode.OK) {
            TempMemory.saveUserId(getApplication().getApplicationContext(),response.getUserId());
            isRegistered.setValue(true);
        }else{
            showError.setValue(true);
        }
    }
}
