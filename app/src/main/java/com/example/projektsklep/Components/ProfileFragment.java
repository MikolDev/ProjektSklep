package com.example.projektsklep.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projektsklep.MainActivity;
import com.example.projektsklep.R;

public class ProfileFragment extends Fragment {
    MainActivity mainActivity;
    TextView profileInfoView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_view, container, false);
        mainActivity = (MainActivity) getActivity();

        profileInfoView = view.findViewById(R.id.profile_info);
        profileInfoView.setText(mainActivity.currentUser.getFirstName());

        return view;
    }
}
