package com.example.testdesign10;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity {

    EditText firstName;
    EditText lastName;
    EditText email;
    EditText password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firstName = findViewById(R.id.FirstName);
        lastName = findViewById(R.id.LastName);
        email = findViewById(R.id.Email);
        password = findViewById(R.id.Password);
        register = findViewById(R.id.RegisterButton);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDataEntered();

                if (isValidPassword(password.getText().toString().trim())){
                    Toast.makeText(RegisterActivity.this, "Done", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "You need at least 6 characters(big + small) and numbers", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public boolean isValidPassword(final String password) {

        Pattern pattern;
        Matcher matcher;

        final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{6,}$";

        pattern = Pattern.compile(PASSWORD_PATTERN);
        matcher = pattern.matcher(password);

        return matcher.matches();

    }

    boolean isEmail (EditText text){
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email)&& Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    boolean isEmpty(EditText text){
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered(){
        if (isEmpty(firstName)){
            firstName.setError("First name is required!");
        }

        if(isEmpty(lastName)){
            lastName.setError("Last name is required!");
        }

        if (isEmail(email) == false){
            email.setError("Enter valid email!");
        }
    }
}
