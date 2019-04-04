package com.example.homie.network;

public interface NetworkConnection {

    void loginAccount(String email, String password);
    void createAccount(String firstName, String lastName, String email, String passport);
}
