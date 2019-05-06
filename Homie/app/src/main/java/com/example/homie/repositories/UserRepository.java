package com.example.homie.repositories;


import com.example.homie.network.APIConnection;
import com.example.homie.network.NetworkConnection;
import com.example.homie.viewModels.AuthCallBack;

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

    public void createAccount(String firstName, String lastName, String email, String password, AuthCallBack viewModel) {
        connection.createAccount(firstName, lastName, email, password, viewModel);
    }

    public void loginAccount(String email, String password, AuthCallBack viewModel) {
        connection.loginAccount(email, password, viewModel);
    }

    public void logoutAccount(String userId){
        //TODO network code for logout
    }
}
