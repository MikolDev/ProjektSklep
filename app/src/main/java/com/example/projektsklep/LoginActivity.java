package com.example.projektsklep;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.projektsklep.R;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText editEmail;
    EditText editPassword;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        editEmail = findViewById(R.id.login_email);
        editPassword = findViewById(R.id.login_password);
        submit = findViewById(R.id.login_submit);

        submit.setOnClickListener((view -> {
            
        }));
    }
}