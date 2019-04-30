package com.example.homie.view;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homie.DRO.AuthDRO;
import com.example.homie.DRO.MovementDRO;
import com.example.homie.DTO.UserLoginDTO;
import com.example.homie.R;
import com.example.homie.network.retrofit.LoginRequest;
import com.example.homie.network.retrofit.MovementRequest;
import com.example.homie.viewModel.AuthCallBack;
import com.example.homie.viewModel.LoginViewModel;
import com.example.homie.viewModel.MovementSensorCallBack;

import java.util.List;

import okhttp3.Response;


public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;

    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        
        initLoginButton();

        initViewModel();

    }

    void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        viewModel.getIsLoggedIn().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        viewModel.getIsValidEmail().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (!aBoolean) {
                    email.setError("Wrong Email");
                }
            }
        });

        viewModel.getIsValidPassword().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (!aBoolean) {
                    password.setError("Wrong Password");
                }
            }
        });

        viewModel.getShowError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    Toast.makeText(LoginActivity.this, "Some error occurred!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void initLoginButton() {
        Button login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.loginUser(email.getText().toString().trim(), password.getText().toString().trim());
            }
        });
    }

//    void testMovRequest() {
//        MovementRequest request = new MovementRequest();
//        request.start(1234, (MovementSensorCallBack) this);
//    }
//
//    void testLoginRequest() {
//        LoginRequest request = new LoginRequest();
//        request.start(new UserLoginDTO("Sneakyjson@user.com", "VerySecure"), this);
//    }

    public void createAccountActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }


}
