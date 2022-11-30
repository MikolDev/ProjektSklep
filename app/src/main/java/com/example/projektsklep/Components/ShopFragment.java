package com.example.projektsklep.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projektsklep.MainActivity;
import com.example.projektsklep.R;

public class ShopFragment extends Fragment {
    MainActivity mainActivity;
    Spinner pcSpinner;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_view, container, false);
        mainActivity = (MainActivity) getActivity();

        pcSpinner = view.findViewById(R.id.spinner_pc);


        return view;
    }
}
