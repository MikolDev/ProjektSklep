package com.example.projektsklep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projektsklep.Account.User;
import com.example.projektsklep.R;

public class RegisterActivity extends AppCompatActivity {
    private MainActivity mainActivity;
    User user;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String password;
    private String passwordRepeat;
    private EditText editFirstName;
    private EditText editLastName;
    private EditText editEmail;
    private EditText editPhoneNumber;
    private EditText editPassword;
    private EditText editPasswordRepeat;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(getApplicationContext());

        initEditText();

        // Register button
        Button submitButton = findViewById(R.id.register_submit);
        submitButton.setOnClickListener(v -> {
            getData();

            if (validate()) {
                user = new User();

                user.setFirstName(firstName);
                user.setLastName(lastName);
                user.setEmail(email);
                user.setPhoneNumber(phoneNumber);
                user.setPassword(password);

                dbHelper.addUser(user);

                Toast.makeText(getApplicationContext(), getString(R.string.account_created), Toast.LENGTH_LONG).show();

                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.wrong_data), Toast.LENGTH_LONG).show();
            }
        });
    }

    // Initialize form inputs
    private void initEditText() {
        editFirstName = findViewById(R.id.register_first_name);
        editLastName = findViewById(R.id.register_last_name);
        editEmail = findViewById(R.id.register_email);
        editPhoneNumber = findViewById(R.id.register_phone_number);
        editPassword = findViewById(R.id.register_password);
        editPasswordRepeat = findViewById(R.id.register_password_repeat);
    }

    // Get user's data from form
    private void getData() {
        firstName = editFirstName.getText().toString().trim();
        lastName = editLastName.getText().toString().trim();
        email = editEmail.getText().toString().trim();
        phoneNumber = editPhoneNumber.getText().toString().trim();
        password = editPassword.getText().toString().trim();
        passwordRepeat = editPasswordRepeat.getText().toString().trim();
    }

    // Form validation
    private boolean validate() {
        if (firstName.isEmpty()
                || lastName.isEmpty()
                || email.isEmpty()
                || phoneNumber.isEmpty()
                || password.isEmpty()
                || !password.equals(passwordRepeat)) {
            return false;
        }
        return true;
    }
}