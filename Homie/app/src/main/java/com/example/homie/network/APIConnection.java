package com.example.homie.network;


import android.util.Log;

import androidx.annotation.NonNull;

import com.example.homie.DTO.UserRegisterDTO;
import com.example.homie.network.retrofit.CO2Request;
import com.example.homie.network.retrofit.HumidityRequest;
import com.example.homie.network.retrofit.MovementRequest;
import com.example.homie.network.retrofit.NotificationRequest;
import com.example.homie.network.retrofit.RegisterRequest;
import com.example.homie.network.retrofit.TemperatureRequest;
import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.SensorDataCallBack;
import com.example.homie.viewModels.util.StatusCode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;

public class APIConnection implements NetworkConnection, NotificationsService {

    private FirebaseAuth auth;
    private static APIConnection instance;

    private APIConnection() {
        auth = FirebaseAuth.getInstance();
    }

    public static APIConnection getInstance() {
        if (instance == null) {
            instance = new APIConnection();
        }
        return instance;
    }

    @Override
    public void loginAccount(String email, String password, final AuthCallBack viewModel) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    viewModel.onReturn(StatusCode.OK);
                    updateNotificationToken();
                    // get User session token
                    //Log.d("TOKEN","Bearer "+auth.getCurrentUser().getIdToken(false).getResult().getToken());
                    // get User notification token
                    /*
                    FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                        @Override
                        public void onComplete(@NonNull Task<InstanceIdResult> task) {
                            Log.d("NotificationsTOKEN",""+ task.getResult().getToken());
                        }
                    });
                    */
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
                    updateNotificationToken();
                }
            }
        });
    }

    @Override
    public void updateNotificationToken() {
        final String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        final String userId = auth.getCurrentUser().getUid();
        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String notificationToken = instanceIdResult.getToken();
                Log.d("```````````````````", "" + notificationToken);
                new NotificationRequest().setNotificationToken(token, userId, notificationToken);
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
}
