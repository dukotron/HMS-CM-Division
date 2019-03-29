package com.example.homie.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import com.example.homie.R;
import com.example.homie.ViewModel.LoginViewModel;


public class LoginActivity extends AppCompatActivity {
    LoginViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViewModel();
    }

    void initViewModel(){
        viewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    public void createAccountActivity(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
        finish();
    }
}
