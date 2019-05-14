package com.example.homie.views;

import android.content.Intent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.R;
import com.example.homie.network.retrofit.MovementRequest;
import com.example.homie.viewModels.LoginViewModel;
import com.example.homie.viewModels.SensorDataCallBack;


public class LoginActivity extends AppCompatActivity {

    EditText email;
    EditText password;
    ProgressBar progressBar;

    LoginViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.emailLogin);
        password = findViewById(R.id.passwordLogin);
        progressBar = findViewById(R.id.progressBar_login);

        initLoginButton();
        initViewModel();
    }

    void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

        viewModel.getIsLoggedIn().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        viewModel.getIsValidEmail().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (!aBoolean) {
                    progressBar.setVisibility(View.GONE);
                    email.setError("Wrong Email");
                }
            }
        });

        viewModel.getIsValidPassword().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (!aBoolean) {
                    progressBar.setVisibility(View.GONE);
                    password.setError("Wrong Password");
                }
            }
        });

        viewModel.getShowError().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    progressBar.setVisibility(View.GONE);
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
                progressBar.setVisibility(View.VISIBLE);
                viewModel.loginUser(email.getText().toString().trim(), password.getText().toString().trim());
            }
        });
    }

    public void createAccountActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}
