package com.example.homie.ViewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.EditText;

import com.example.homie.Repository.UserRepository;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterViewModel extends AndroidViewModel {
    private MutableLiveData<Boolean> isValidFirstName;
    private MutableLiveData<Boolean> isValidLastName;
    private MutableLiveData<Boolean> isValidEmail;
    private MutableLiveData<Boolean> isValidPassword;

    private MutableLiveData<Boolean> isRegistered;

    private UserRepository userRepository;
    public RegisterViewModel(@NonNull Application application) {
        super(application);
        userRepository = UserRepository.getInstance();

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

    public void registerUser(String firstName,String lastName,String email,String password){
        if(checkEnteredData(firstName,lastName,email,password)){
            userRepository.createNewAccount(firstName,lastName,email,password);
            isRegistered.setValue(true);
        }
    }

    private boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    boolean isEmail (String email){
        return (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean checkEnteredData(String firstName,String lastName,String email,String password){
        boolean valid = true;
        if (firstName.isEmpty()){
            isValidFirstName.setValue(false);
            valid = false;
        }

        if(lastName.isEmpty()){
            isValidLastName.setValue(false);
            valid = false;
        }

        if (!isEmail(email)){
            isValidEmail.setValue(false);
            valid = false;
        }

        if(!isValidPassword(password)){
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
