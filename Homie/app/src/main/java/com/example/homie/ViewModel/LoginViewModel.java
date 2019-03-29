package com.example.homie.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import com.example.homie.Repository.UserRepository;
import com.example.homie.ViewModel.Util.InputDataValidator;

public class LoginViewModel extends AndroidViewModel {

    private MutableLiveData<Boolean> isValidEmail;
    private MutableLiveData<Boolean> isValidPassword;

    private MutableLiveData<Boolean> isLoggedIn;

    private UserRepository userRepository;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();

        isValidEmail = new MutableLiveData<>();
        isValidPassword = new MutableLiveData<>();
        isLoggedIn = new MutableLiveData<>();

        isValidEmail.setValue(true);
        isValidPassword.setValue(true);
        isLoggedIn.setValue(false);
    }

    public void loginUser(String email, String password) {
        if (checkEnteredData(email, password)) {
            userRepository.loginAccount(email, password);
            isLoggedIn.setValue(true);
        }

    }

    boolean checkEnteredData(String email, String password) {
        boolean valid = true;

        if (!InputDataValidator.isEmail(email)) {
            isValidEmail.setValue(false);
            valid = false;
        }

        if (!InputDataValidator.isValidPassword(password)) {
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
}
