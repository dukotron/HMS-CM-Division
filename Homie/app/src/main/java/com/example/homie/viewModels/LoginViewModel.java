package com.example.homie.viewModels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;
import androidx.annotation.NonNull;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.repositories.UserRepository;
import com.example.homie.viewModels.util.InputDataValidator;
import com.example.homie.viewModels.util.TempMemory;

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
       // isLoggedIn.setValue(true);

    }

    private boolean checkEnteredData(String email, String password) {
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
        if (response.getStatusCode() == 0) {
            TempMemory.saveUserId(getApplication().getApplicationContext(),response.getUserId());
            isLoggedIn.setValue(true);
        }else{
            showError.setValue(true);
        }
    }


}
