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
import android.widget.Toast;

import com.example.homie.DRO.SensorDRO;
import com.example.homie.R;
import com.example.homie.network.retrofit.MovementRequest;
import com.example.homie.viewModels.LoginViewModel;
import com.example.homie.viewModels.SensorDataCallBack;


public class LoginActivity extends AppCompatActivity{

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
//
//    void testMovRequest() {
//        String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6IjM4MDEwNjVlNGI1NjNhZWVlZWIzNTkwOTEwZDlmOTc3YTgxMjMwOWEiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL3NlY3VyZXRva2VuLmdvb2dsZS5jb20vaG9taWUtYjAxYzYiLCJhdWQiOiJob21pZS1iMDFjNiIsImF1dGhfdGltZSI6MTU1NzEyNjI4MCwidXNlcl9pZCI6ImJFVTBsdHkxNGJldmZrRm9pWFVJZkNHQW9KMzIiLCJzdWIiOiJiRVUwbHR5MTRiZXZma0ZvaVhVSWZDR0FvSjMyIiwiaWF0IjoxNTU3MTI2MjgwLCJleHAiOjE1NTcxMjk4ODAsImVtYWlsIjoidGVzdEB0ZXN0LnRlc3QiLCJlbWFpbF92ZXJpZmllZCI6ZmFsc2UsImZpcmViYXNlIjp7ImlkZW50aXRpZXMiOnsiZW1haWwiOlsidGVzdEB0ZXN0LnRlc3QiXX0sInNpZ25faW5fcHJvdmlkZXIiOiJwYXNzd29yZCJ9fQ.B1UqGjW4ycV7WQIuojkLGHxVZ_UqwDhdv2YTT4RV5aFuwBGWRjKslme8GpvXzHL48wkDvBF7HuzaBlj46fXXIbYc3A4oWgEk4fR6aFBd5eK74uJlSU-FgDVsWoXJeB7dzrmlWWxAA2qPQaj90MM8ggxQikin9yTpHR6al9qRAAMOtsQ7jl3-bm9Zc97FghQVcpGw9LQ911daR_8siV40NdXR8dZTS2V3keoNtZgsnfiSrJqcAx6zdEfGUI5y6AYUj8uf37xXMSm9-c3A3gZUFdUAVBsa9N3aesIqi_Bda4DpnA1heJHYu9I9rFkWY70qqjdQjowqLP0rLEnam-GiAQ";
//        String userId = "bEU0lty14bevfkFoiXUIfCGAoJ32";
//        String from = "2017-09-08T19:01:55.714942";
//        String to = "2019-09-08T19:01:55.714942";
//        new MovementRequest().start(token, userId, this, from, to);
//    }


    public void createAccountActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }

}
