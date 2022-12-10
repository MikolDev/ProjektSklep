package com.example.projektsklep;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.RelativeLayout;

import com.example.projektsklep.Account.User;
import com.example.projektsklep.Components.OrdersFragment;
import com.example.projektsklep.Components.ProfileFragment;
import com.example.projektsklep.Components.ShopFragment;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.gson.Gson;

public class MainActivity extends AppCompatActivity {
    private RelativeLayout fragmentContainer;
    private User currentUser = null;
    public BottomNavigationView bottomNavigationView;
    public BottomAppBar bottomAppBar;
    public DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragmentContainer = findViewById(R.id.fragment_container);
        dbHelper = new DatabaseHelper(getApplicationContext());

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomAppBar = findViewById(R.id.bottomAppBar);
        bottomNavigationView.setSelected(false);

        loadData();

        changeFragment(0);

        initNavigationListener();
    }

    public void loadData() {
        // odtwarzanie currentUsera
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        String savedUser = sharedPreferences.getString("savedUser", null);
        currentUser = new Gson().fromJson(savedUser, User.class);
    }

    public void saveData() {
        // zapisywanie currentUsera
        SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();
        if (currentUser != null) {
            sharedPrefEditor.putString("savedUser", currentUser.parseUserToString());
        } else {
            sharedPrefEditor.putString("savedUser", null);
        }
        sharedPrefEditor.apply();
    }

    @Override
    protected void onPause() {
        super.onPause();

        saveData();
    }

    protected void onResume() {
        super.onResume();

        loadData();

        if (currentUser == null) {
            Intent loginIntent = getIntent();
            int intentUserId = loginIntent.getIntExtra("userId", -1);

            if (intentUserId != -1) {
                currentUser = dbHelper.getUserById(intentUserId);
                Log.v("user", currentUser.getFirstName());
            }
        }
    }

    public void changeFragment(int id) {
        Fragment fragment;

        switch (id) {
            case 0:
                fragment = new ShopFragment();
                break;
            case 1:
                fragment = new OrdersFragment();
                break;
            case 2:
                fragment = new ProfileFragment();
                break;
            default:
                fragment = new ShopFragment();
        }

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
    }

    public void initNavigationListener() {
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.item_shop:
                        changeFragment(0);
                        return true;
                    case R.id.item_orders:
                        changeFragment(1);
                        return true;
                    case R.id.item_profile:
                        changeFragment(2);
                        return true;
                    default:
                        return false;
                }
            }
        });
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
        saveData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_up, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_author:
                showAuthorInfo();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showAuthorInfo() {
        DialogInterface.OnClickListener authorListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                switch(i) {
                    case DialogInterface.BUTTON_POSITIVE:
                        dialogInterface.dismiss();
                        break;
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getString(R.string.author))
                .setMessage(getString(R.string.author_info))
                .setPositiveButton(getString(R.string.ok), authorListener).show();
    }
}