package com.example.homie.network;

import com.example.homie.DTO.UserLoginDTO;
import com.example.homie.DTO.UserRegisterDTO;
import com.example.homie.network.retrofit.LoginRequest;
import com.example.homie.network.retrofit.RegisterRequest;
import com.example.homie.viewModel.AuthCallBack;

public class APIConnection implements NetworkConnection {

    @Override
    public void loginAccount(String email, String password, AuthCallBack viewModel) {
        new LoginRequest().start(new UserLoginDTO(email, password), viewModel);
    }

    @Override
    public void createAccount(String firstName, String lastName, String email, String password, AuthCallBack viewModel) {
        new RegisterRequest().start(new UserRegisterDTO(firstName, lastName, email, password), viewModel);
    }
}
