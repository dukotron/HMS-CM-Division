package com.example.homie.network;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.homie.DTO.UserRegisterDTO;
import com.example.homie.network.retrofit.CO2Request;
import com.example.homie.network.retrofit.HumidityRequest;
import com.example.homie.network.retrofit.MovementRequest;
import com.example.homie.network.retrofit.RegisterRequest;
import com.example.homie.network.retrofit.SettingsRequest;
import com.example.homie.network.retrofit.TemperatureRequest;
import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.SensorDataCallBack;
import com.example.homie.viewModels.util.StatusCode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class APIConnection implements NetworkConnection {

    private FirebaseAuth auth;

    public APIConnection() {
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void loginAccount(String email, String password, final AuthCallBack viewModel) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    viewModel.onReturn(StatusCode.OK);
                    Log.d("TOKEN", "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken());
                }
            }
        });

    }

    @Override
    public void createAccount(final String firstName, final String lastName, final String email, final String password, final AuthCallBack viewModel) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    String userId = auth.getCurrentUser().getUid();
                    new RegisterRequest().start(new UserRegisterDTO(userId), viewModel);
                }
            }
        });
    }

    @Override
    public void getMovementData(final SensorDataCallBack viewModel) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new MovementRequest().start(token, userId, viewModel);
    }

    @Override
    public void getCo2(final SensorDataCallBack callBack) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new CO2Request().start(token, userId, callBack);
    }

    @Override
    public void getTemperatureData(final SensorDataCallBack callBack) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new TemperatureRequest().start(token, userId, callBack);
    }

    @Override
    public void getHumidityData(final SensorDataCallBack callBack) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new HumidityRequest().start(token, userId, callBack);
    }

    @Override
    public void setAtHome(boolean atHome) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new SettingsRequest().setAtHome(token, userId, atHome);
    }
}
