package com.example.projektsklep;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projektsklep.R;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper dbHelper;
    EditText editEmail;
    EditText editPassword;
    Button submit;
    Button register;

    /**
     * Metoda inicjalizuje przycisk logowania się i reaguje na wykorzystanie formularza.
     *
     * @param savedInstanceState stan klasy
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new DatabaseHelper(this);
        editEmail = findViewById(R.id.login_email);
        editPassword = findViewById(R.id.login_password);
        submit = findViewById(R.id.login_submit);

        submit.setOnClickListener(view -> {
            String email = editEmail.getText().toString().trim();
            String password = editPassword.getText().toString().trim();

            if (validate(email, password) && dbHelper.checkUser(email, password)) {
                Toast.makeText(getApplicationContext(), getString(R.string.login_welcome), Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent(LoginActivity.this, MainActivity.class);
                returnIntent.putExtra("userId", dbHelper.getUserData(email, password).getId());
                startActivity(returnIntent);
            } else {
                Toast.makeText(getApplicationContext(), getString(R.string.wrong_data), Toast.LENGTH_SHORT).show();
            }
        });

        register = findViewById(R.id.login_create_account);
        register.setOnClickListener(view -> {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(registerIntent);
        });
    }

    /**
     * Metoda waliduje dane.
     *
     * @param email email do walidacji
     * @param password hasło do walidacji
     * @return czy dane poprawne
     */
    private boolean validate(String email, String password) {
        if (email.isEmpty() || password.isEmpty()) return false;
        return true;
    }
}