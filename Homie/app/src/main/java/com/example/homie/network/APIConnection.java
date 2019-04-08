package com.example.homie.network;

import com.example.homie.network.DTO.UserLoginDTO;
import com.example.homie.network.DTO.UserRegisterDTO;
import com.example.homie.network.retrofit.LoginRequest;
import com.example.homie.network.retrofit.RegisterRequest;

public class APIConnection implements NetworkConnection {

    @Override
    public void loginAccount(String email, String password) {
        new LoginRequest().start(new UserLoginDTO(email, password));
    }

    @Override
    public void createAccount(String firstName, String lastName, String email, String password) {
        new RegisterRequest().start(new UserRegisterDTO(firstName, lastName, email, password));
    }
}
