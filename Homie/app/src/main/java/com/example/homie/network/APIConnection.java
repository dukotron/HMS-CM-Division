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
import com.example.homie.network.util.DateFormatConverter;
import com.example.homie.viewModels.util.StatusCode;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Date;

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
    public void getMovementData(final SensorDataCallBack viewModel, Date dateFrom, Date dateTo) {
        String token = "Bearer "+auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        //JUST FOR TESTING
//        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjM4MDEwNjVlNGI1NjNhZWVlZWIzNTkwOTEwZDlmOTc3YTgxMjMwOWEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vaG9taWUtYjAxYzYiLCJhdWQiOiJob21pZS1iMDFjNiIsImF1dGhfdGltZSI6MTU1NzEyNjI4MCwidXNlcl9pZCI6ImJFVTBsdHkxNGJldmZrRm9pWFVJZkNHQW9KMzIiLCJzdWIiOiJiRVUwbHR5MTRiZXZma0ZvaVhVSWZDR0FvSjMyIiwiaWF0IjoxNTU3MTI2MjgwLCJleHAiOjE1NTcxMjk4ODAsImVtYWlsIjoidGVzdEB0ZXN0LnRlc3QiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsidGVzdEB0ZXN0LnRlc3QiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.B1UqGjW4ycV7WQIuojkLGHxVZ_UqwDhdv2YTT4RV5aFuwBGWRjKslme8GpvXzHL48wkDvBF7HuzaBlj46fXXIbYc3A4oWgEk4fR6aFBd5eK74uJlSU-FgDVsWoXJeB7dzrmlWWxAA2qPQaj90MM8ggxQikin9yTpHR6al9qRAAMOtsQ7jl3-bm9Zc97FghQVcpGw9LQ911daR_8siV40NdXR8dZTS2V3keoNtZgsnfiSrJqcAx6zdEfGUI5y6AYUj8uf37xXMSm9-c3A3gZUFdUAVBsa9N3aesIqi_Bda4DpnA1heJHYu9I9rFkWY70qqjdQjowqLP0rLEnam-GiAQ";
//        String userId = "bEU0lty14bevfkFoiXUIfCGAoJ32";
//        String from = "2017-09-08T19:01:55.714942";
//        String to = "2019-09-08T19:01:55.714942";
        new MovementRequest().start(token, userId, viewModel, from, to);
    }

    @Override
    public void getCo2(final SensorDataCallBack callBack, Date dateFrom, Date dateTo) {
        String token = "Bearer "+auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new CO2Request().start(token, userId, callBack, from, to);
    }

    @Override
    public void getTemperatureData(final SensorDataCallBack callBack, Date dateFrom, Date dateTo) {
        String token = "Bearer "+auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new TemperatureRequest().start(token, userId, callBack, from, to);
    }

    @Override
    public void getHumidityData(final SensorDataCallBack callBack, Date dateFrom, Date dateTo) {
        String token = "Bearer "+auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        String from = DateFormatConverter.convertDateToDotNetFormat(dateFrom);
        String to = DateFormatConverter.convertDateToDotNetFormat(dateTo);
        new HumidityRequest().start(token, userId, callBack, from, to);
    }

    @Override
    public void setAtHome(boolean atHome) {
        String token = "Bearer " + auth.getCurrentUser().getIdToken(false).getResult().getToken();
        String userId = auth.getCurrentUser().getUid();
        new SettingsRequest().setAtHome(token, userId, atHome);
    }
}
