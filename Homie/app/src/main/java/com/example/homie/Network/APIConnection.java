package com.example.homie.Network;

public interface APIConnection {
    void createNewAccount(String firstName, String lastName, String email, String password);

    void loginAccount(String email, String password);
}
