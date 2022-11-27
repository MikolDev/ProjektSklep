package com.example.projektsklep.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projektsklep.MainActivity;
import com.example.projektsklep.R;

public class ProfileFragment extends Fragment {
    MainActivity mainActivity;
    TextView profileInfoView;
    Button logoutButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_view, container, false);
        mainActivity = (MainActivity) getActivity();

        profileInfoView = view.findViewById(R.id.profile_info);

        if (mainActivity.currentUser == null) {
            profileInfoView.setText("Niezalogowany");
        } else {
            profileInfoView.setText(mainActivity.currentUser.getFirstName());
        }

        logoutButton = view.findViewById(R.id.logout_button);
        logoutButton.setOnClickListener(v -> {
            if (mainActivity.currentUser != null) {
                mainActivity.currentUser = null;
                mainActivity.changeFragment(0);
                Toast.makeText(mainActivity.getApplicationContext(), R.string.logout_success, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(mainActivity.getApplicationContext(), "Jesteś wylogowany", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
