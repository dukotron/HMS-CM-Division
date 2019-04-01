package com.example.homie.view;


import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.homie.R;
import com.example.homie.viewModel.RegisterViewModel;


public class RegisterActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;

    RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);

        initRegisterButton();
        initPasswordButton();
        initGoToLoginButton();

        initViewModel();
    }

    void initViewModel() {
        viewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);

        viewModel.getIsRegistered().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (aBoolean) {
                    startActivity(new Intent(RegisterActivity.this, NavigationActivity.class));
                    finish();
                }
            }
        });

        viewModel.getIsValidFirstName().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (!aBoolean) {
                    firstName.setError("Wrong First Name");
                }
            }
        });

        viewModel.getIsValidLastName().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (!aBoolean) {
                    lastName.setError("Wrong Last Name");
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
    }

    void initRegisterButton() {
        Button registerBtn = findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.registerUser(firstName.getText().toString(), lastName.getText().toString(),
                        email.getText().toString(), password.getText().toString().trim());
            }
        });
    }

    void initGoToLoginButton() {
        Button loginBtn = findViewById(R.id.go_to_login);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    void initPasswordButton() {
        //when button is touched, the password becomes
        // visible as long as the button is still pressed by the user
        Button showPasswordBtn = findViewById(R.id.showPasswordButton);
        showPasswordBtn.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_UP:
                        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        break;

                    case MotionEvent.ACTION_DOWN:
                        password.setInputType(InputType.TYPE_CLASS_TEXT);
                        break;

                }
                return true;
            }
        });
    }

}
