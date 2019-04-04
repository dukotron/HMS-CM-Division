package com.example.homie.repository;


import com.example.homie.network.APIConnection;
import com.example.homie.network.NetworkConnection;

public class UserRepository {

    private NetworkConnection connection;
    private static UserRepository instance;

    private UserRepository() {
        connection = new APIConnection();
    }

    public static UserRepository getInstance() {
        if (instance == null) {
            instance = new UserRepository();
        }
        return instance;
    }

    public void createAccount(String firstName, String lastName, String email, String password) {
        connection.createAccount(firstName, lastName, email, password);
    }

    public void loginAccount(String email, String password) {
        connection.loginAccount(email, password);
    }
}
