package com.example.homie.network;

import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.MovementSensorCallBack;

public interface NetworkConnection {

    void loginAccount(String email, String password, AuthCallBack callBack);
    void createAccount(String firstName, String lastName, String email, String passport, AuthCallBack callBack);
    void getMovementData(int userId, MovementSensorCallBack callBack);
}
