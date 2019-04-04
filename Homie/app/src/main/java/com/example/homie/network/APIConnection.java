package com.example.homie.network;

import com.example.homie.network.DTO.UserLoginDTO;
import com.example.homie.network.DTO.UserRegisterDTO;
import com.example.homie.network.retrofit.LoginRequest;
import com.example.homie.network.retrofit.RegisterRequest;

public class APIConnection implements NetworkConnection {

    private LoginRequest loginRequest;
    private RegisterRequest registerRequest;


    @Override
    public void loginAccount(String email, String password) {
        UserLoginDTO user = new UserLoginDTO(email, password);
        loginRequest.start(user);
    }

    @Override
    public void createAccount(String firstName, String lastName, String email, String password) {
        UserRegisterDTO user = new UserRegisterDTO(firstName, lastName, email, password);
        registerRequest.start(user);
    }
}
