package com.example.projektsklep.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projektsklep.MainActivity;
import com.example.projektsklep.R;

public class OrdersFragment extends Fragment {
    MainActivity mainActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orders_view, container, false);
        mainActivity = (MainActivity) getActivity();

        if (mainActivity.getCurrentUser() == null) {
            Toast.makeText(mainActivity.getApplicationContext(), getString(R.string.log_in_orders), Toast.LENGTH_SHORT).show();
        }

        return view;
    }
}
