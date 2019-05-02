package com.example.homie.viewModel.util;

import android.util.Patterns;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputDataValidator {

    public static boolean isEmailValid(String email) {
        return (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    public static boolean isPasswordValid(final String password) {
        final String PASSWORD_PATTERN = "[\\w+~@#$%^&*+=`|{}:;!.?\"()\\[\\]-]{8,128}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public static boolean isStringValid(final String inputString){
        final String STRING_PATTERN = "^[A-Za-z]{1,20}$";
        Pattern pattern = Pattern.compile(STRING_PATTERN);
        Matcher matcher = pattern.matcher(inputString);
        return matcher.matches();
    }
}
