package com.example.homie.network;

import com.example.homie.network.DTO.UserLoginDTO;
import com.example.homie.network.DTO.UserRegisterDTO;
import com.example.homie.network.retrofit.LoginRequest;
import com.example.homie.network.retrofit.RegisterRequest;
import com.example.homie.network.util.PasswordUtil;

public class APIConnection implements NetworkConnection {

    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;


    @Override
    public void loginAccount(String email, String password) {
        byte[] salt = PasswordUtil.generateSalt();
        UserLoginDTO user = new UserLoginDTO(email, PasswordUtil.hashPassword(password.toCharArray(), salt, 128, 256),salt);
        loginRequest.start(user);
    }

    @Override
    public void createAccount(String firstName, String lastName, String email, String password) {
        byte[] salt = PasswordUtil.generateSalt();
        UserRegisterDTO user = new UserRegisterDTO(firstName, lastName, email, PasswordUtil.hashPassword(password.toCharArray(), salt, 128, 256),salt);
        registerRequest.start(user);
    }
}
