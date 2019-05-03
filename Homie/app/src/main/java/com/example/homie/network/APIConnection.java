package com.example.homie.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.homie.DTO.UserLoginDTO;
import com.example.homie.DTO.UserRegisterDTO;
import com.example.homie.network.retrofit.LoginRequest;
import com.example.homie.network.retrofit.MovementRequest;
import com.example.homie.network.retrofit.RegisterRequest;
import com.example.homie.viewModels.AuthCallBack;
import com.example.homie.viewModels.MovementSensorCallBack;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class APIConnection implements NetworkConnection {

    private FirebaseAuth auth;

    public APIConnection(){
        auth = FirebaseAuth.getInstance();
    }

    @Override
    public void loginAccount(String email, String password, AuthCallBack viewModel) {
        new LoginRequest().start(new UserLoginDTO(email, password), viewModel);
    }

    @Override
    public void createAccount(String firstName, String lastName, String email, String password, AuthCallBack viewModel) {
        auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    String userId = auth.getCurrentUser().getUid();
                    Log.d("FirebaseRegisterUID",""+userId);
                    //new RegisterRequest().start(new UserRegisterDTO(firstName, lastName, email, password), viewModel);
                }
            }
        });

    }

    @Override
    public void getMovementData(int userId, MovementSensorCallBack viewModel) {
        new MovementRequest().start(userId, viewModel);
    }
}
