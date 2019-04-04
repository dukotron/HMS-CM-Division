package com.example.homie.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.example.homie.repository.UserRepository;
import com.example.homie.viewModel.util.InputDataValidator;

public class RegisterViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> isValidFirstName;
    private MutableLiveData<Boolean> isValidLastName;
    private MutableLiveData<Boolean> isValidEmail;
    private MutableLiveData<Boolean> isValidPassword;

    private MutableLiveData<Boolean> isRegistered;

    private UserRepository userRepository;
    //TODO move IP address to separate configuration class


    public RegisterViewModel(@NonNull Application application) {
        super(application);
        //userRepository = UserRepository.getInstance();

        isValidFirstName = new MutableLiveData<>();
        isValidLastName = new MutableLiveData<>();
        isValidEmail = new MutableLiveData<>();
        isValidPassword = new MutableLiveData<>();
        isRegistered = new MutableLiveData<>();

        isValidFirstName.setValue(true);
        isValidLastName.setValue(true);
        isValidEmail.setValue(true);
        isValidPassword.setValue(true);
        isRegistered.setValue(false);
    }

    public void registerUser(String firstName, String lastName, String email, String password) {
        if (checkEnteredData(firstName, lastName, email, password)) {
            //userRepository.createAccount(firstName, lastName, email, password);
            isRegistered.setValue(true);
            //new RegistrationRequest().start(firstName, lastName, email, password);
        }
    }

    boolean checkEnteredData(String firstName, String lastName, String email, String password) {
        boolean valid = true;
        if (firstName.isEmpty()) {
            isValidFirstName.setValue(false);
            valid = false;
        }

        if (lastName.isEmpty()) {
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


}
