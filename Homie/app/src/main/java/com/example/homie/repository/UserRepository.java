package com.example.homie.repository;

import com.example.homie.network.APIConnection;
import com.example.homie.network.DWHConnection;

public class UserRepository {

    private APIConnection apiConnection;
    private static UserRepository instance;

    private UserRepository() {
        apiConnection = new DWHConnection();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void createAccount(String firstName, String lastName, String email, String password) {
        apiConnection.createAccount(firstName, lastName, email, password);
    }

    public void loginAccount(String email, String password) {
        apiConnection.loginAccount(email, password);
    }
}
