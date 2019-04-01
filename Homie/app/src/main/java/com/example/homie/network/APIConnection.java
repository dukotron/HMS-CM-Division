package com.example.homie.network;

public interface APIConnection {

    void createAccount(String firstName, String lastName, String email, String password);

    void loginAccount(String email, String password);
}
