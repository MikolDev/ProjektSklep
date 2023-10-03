package com.example.projektsklep.Components;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import com.example.projektsklep.Account.User;
import com.example.projektsklep.LoginActivity;
import com.example.projektsklep.MainActivity;
import com.example.projektsklep.R;

public class ProfileFragment extends Fragment {
    MainActivity mainActivity;
    TextView profileInfoView;
    Button logoutButton;

    /**
     * Metoda pokazuje informacje o użytkowniku, jeżeli jest zalogowany. Umożliwia również wylogowanie się.
     *
     * @param inflater inflater widoku profilu
     * @param container
     * @param savedInstanceState stan fragmentu
     * @return widok profilu
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_view, container, false);
        mainActivity = (MainActivity) getActivity();

        profileInfoView = view.findViewById(R.id.profile_info);
        User currentUser = mainActivity.getCurrentUser();

        if (currentUser == null) {
            profileInfoView.setText(getString(R.string.not_logged_in));
        } else {
            String profileInfo = currentUser.getFirstName()
                    + " \n"
                    + currentUser.getLastName()
                    + "\n"
                    + currentUser.getEmail()
                    + "\n"
                    + currentUser.getPhoneNumber();
            profileInfoView.setText(profileInfo);
        }

        logoutButton = view.findViewById(R.id.logout_button);

        if (currentUser == null) {
            logoutButton.setText(getString(R.string.login_welcome));
        } else {
            logoutButton.setText(getString(R.string.logout));
        }

        logoutButton.setOnClickListener(v -> {
            Intent loginIntent = new Intent(getContext(), LoginActivity.class);
            startActivity(loginIntent);

            if (currentUser != null) {
                mainActivity.setCurrentUser(null);
                Toast.makeText(mainActivity.getApplicationContext(), R.string.logout_success, Toast.LENGTH_SHORT).show();
            }
        });

        SwitchCompat switchCompat = view.findViewById(R.id.save_user_switch);

        if (currentUser == null) {
            switchCompat.setVisibility(View.GONE);
        } else {
            switchCompat.setVisibility(View.VISIBLE);
        }

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    mainActivity.doSaveUser = true;
                } else {
                    mainActivity.doSaveUser = false;
                }
            }
        });

        return view;
    }
}
