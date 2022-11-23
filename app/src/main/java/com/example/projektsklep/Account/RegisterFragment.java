package com.example.projektsklep.Account;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projektsklep.MainActivity;
import com.example.projektsklep.R;

public class RegisterFragment extends Fragment {
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.register_view, container, false);

        mainActivity = (MainActivity) getActivity();
        dbHelper = new DatabaseHelper(mainActivity);

        initEditText(view);

        Button loginButton = view.findViewById(R.id.register_login);
        loginButton.setOnClickListener(v -> mainActivity.changeFragment(0));

        // Register button
        Button submitButton = view.findViewById(R.id.register_submit);
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

                Toast.makeText(getContext(), getString(R.string.account_created), Toast.LENGTH_LONG).show();
                mainActivity.changeFragment(0);
            } else {
                Toast.makeText(getContext(), getString(R.string.wrong_data), Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    // Initialize form inputs
    private void initEditText(View view) {
        editFirstName = view.findViewById(R.id.register_first_name);
        editLastName = view.findViewById(R.id.register_last_name);
        editEmail = view.findViewById(R.id.register_email);
        editPhoneNumber = view.findViewById(R.id.register_phone_number);
        editPassword = view.findViewById(R.id.register_password);
        editPasswordRepeat = view.findViewById(R.id.register_password_repeat);
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