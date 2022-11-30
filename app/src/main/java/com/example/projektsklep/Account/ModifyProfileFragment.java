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

public class ModifyProfileFragment extends Fragment {
    private MainActivity mainActivity;
    private DatabaseHelper dbHelper;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.modify_profile_view, container, false);
        mainActivity = (MainActivity) getActivity();
        dbHelper = new DatabaseHelper(mainActivity);

        initEditText(view);

        return view;
    }

    // Initialize form inputs
    private void initEditText(View view) {

    }

    // Get data from inputs
    private void getData() {

    }

    private boolean validate() {

        return true;
    }
}