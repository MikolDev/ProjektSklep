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

public class LoginFragment extends Fragment {
    private MainActivity mainActivity;
    private EditText editEmail;
    private EditText editPassword;
    private String email;
    private String password;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.login_view, container, false);
        mainActivity = (MainActivity) getActivity();

        Button createAccountButton = view.findViewById(R.id.login_create_account);
        createAccountButton.setOnClickListener(v -> mainActivity.changeFragment(1));

        initEditText(view);

        // Logging button
        Button submitButton = view.findViewById(R.id.login_submit);
        submitButton.setOnClickListener(v -> {
            getData();

            if (validate()) {
                Toast.makeText(getContext(), "Correct data", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Wrong data", Toast.LENGTH_LONG).show();
            }
        });

        return view;
    }

    // Initialize form inputs
    private void initEditText(View view) {
        editEmail = view.findViewById(R.id.login_email);
        editPassword = view.findViewById(R.id.login_password);
    }

    private void getData() {
        email = editEmail.getText().toString().trim();
        password = editPassword.getText().toString().trim();
    }

    private boolean validate() {
        if (email.isEmpty() || password.isEmpty()) return false;
        return true;
    }
}