package com.example.homie.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.repository.UserRepository;
import com.example.homie.viewModel.util.InputDataValidator;
import com.example.homie.viewModel.util.StatusCode;

public class LoginViewModel extends AndroidViewModel implements AuthCallBack{

    private MutableLiveData<Boolean> isValidEmail;
    private MutableLiveData<Boolean> isValidPassword;

    private MutableLiveData<Boolean> isLoggedIn;

    private MutableLiveData<Boolean> showError;
    private UserRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();

        isValidEmail = new MutableLiveData<>();
        isValidPassword = new MutableLiveData<>();
        isLoggedIn = new MutableLiveData<>();
        showError = new MutableLiveData<>();

        isValidEmail.setValue(true);
        isValidPassword.setValue(true);
        isLoggedIn.setValue(false);
        showError.setValue(false);
    }

    public void loginUser(String email, String password) {
        if (checkEnteredData(email, password)) {
            userRepository.loginAccount(email, password, this);
        }
    }

    boolean checkEnteredData(String email, String password) {
        boolean valid = true;

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

    public MutableLiveData<Boolean> getIsValidEmail() {
        return isValidEmail;
    }

    public MutableLiveData<Boolean> getIsValidPassword() {
        return isValidPassword;
    }

    public MutableLiveData<Boolean> getIsLoggedIn() {
        return isLoggedIn;
    }

    public MutableLiveData<Boolean> getShowError() {
        return showError;
    }

    @Override
    public void onReturn(AuthDRO response) {
        if (response.getStatusCode() == StatusCode.OK) {
            isLoggedIn.setValue(true);
        }else{
            showError.setValue(true);
        }
    }
}
