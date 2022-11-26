package com.example.projektsklep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.projektsklep.Account.LoginFragment;
import com.example.projektsklep.Account.RegisterFragment;
import com.example.projektsklep.Account.User;
import com.example.projektsklep.Components.OrdersFragment;
import com.example.projektsklep.Components.ProfileFragment;
import com.example.projektsklep.Components.ShopFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout fragmentContainer;
    public User currentUser;
    public BottomNavigationView bottomNavigationView;
    public BottomAppBar bottomAppBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer = findViewById(R.id.fragment_container);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomAppBar = findViewById(R.id.bottomAppBar);

        changeFragment(0);

        // jeśli użytkownik jest zalogowany
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_shop:
                        changeFragment(2);
                        Toast.makeText(getApplicationContext(), "click", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item_orders:
                        changeFragment(3);
                        return true;
                    case R.id.item_profile:
                        changeFragment(4);
                        return true;
                    default:
                        return false;
                }
            }
        });

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
            case 2:
                fragment = new ShopFragment();
                break;
            case 3:
                fragment = new OrdersFragment();
                break;
            case 4:
                fragment = new ProfileFragment();
                break;
            default:
                fragment = new LoginFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }
}