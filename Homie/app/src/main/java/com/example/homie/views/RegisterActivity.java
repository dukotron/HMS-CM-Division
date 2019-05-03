package com.example.homie.views;


import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.homie.R;
import com.example.homie.viewModels.RegisterViewModel;


public class RegisterActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    ProgressBar progressBar;

    RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        email = findViewById(R.id.emailRegister);
        password = findViewById(R.id.passwordRegister);
        progressBar = findViewById(R.id.progressBar_register);

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
                    progressBar.setVisibility(View.GONE);
                    startActivity(new Intent(RegisterActivity.this, MainActivity.class));
                    finish();
                }
            }
        });

        viewModel.getIsValidFirstName().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (!aBoolean) {
                    progressBar.setVisibility(View.GONE);
                    firstName.setError("Wrong First Name");
                }
            }
        });

        viewModel.getIsValidLastName().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean aBoolean) {
                if (!aBoolean) {
                    progressBar.setVisibility(View.GONE);
                    lastName.setError("Wrong Last Name");
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
                    Toast.makeText(RegisterActivity.this, "Some error occurred!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    void initRegisterButton() {
        Button registerBtn = findViewById(R.id.registerButton);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setVisibility(View.VISIBLE);
                viewModel.registerUser(firstName.getText().toString().trim(), lastName.getText().toString().trim(),
                        email.getText().toString().trim(), password.getText().toString().trim());
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
