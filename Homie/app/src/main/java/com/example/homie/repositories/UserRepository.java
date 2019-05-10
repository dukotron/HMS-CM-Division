package com.example.homie.repositories;

import com.example.homie.network.APIConnection;
import com.example.homie.network.NetworkConnection;
import com.example.homie.viewModels.AddDeviceCallback;
import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.DevicesCallback;

public class UserRepository {

    private NetworkConnection connection;
    private static UserRepository instance;


    private UserRepository() {
        connection = APIConnection.getInstance();
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

    public void logoutAccount() {
        connection.logoutAccount();
    }

    public void addDevice(String deviceLocation, String deviceId, AddDeviceCallback viewModel) {
        connection.addDevice(deviceLocation, deviceId, viewModel);
    }

    public void deleteDevice(String deviceId){
        connection.deleteDevice(deviceId);
    }

    public void getUserDevices(DevicesCallback viewModel){
        connection.getUserDevices(viewModel);
    }

    public void setAtHome(boolean atHome) {
        connection.setAtHome(atHome);
    }
}
