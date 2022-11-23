package com.example.projektsklep;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.RelativeLayout;

import com.example.projektsklep.Account.LoginFragment;
import com.example.projektsklep.Account.RegisterFragment;
import com.example.projektsklep.Account.User;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout fragmentContainer;
    public User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer = findViewById(R.id.fragment_container);

        changeFragment(0);
    }

    public void changeFragment(int id) {
        Fragment fragment;

        switch (id) {
            case 0:
                fragment = new LoginFragment();
                break;
            case 1:
                fragment = new RegisterFragment();
                break;
            default:
                fragment = new LoginFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}