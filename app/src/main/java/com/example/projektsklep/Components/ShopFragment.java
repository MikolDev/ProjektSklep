package com.example.projektsklep.Components;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projektsklep.DatabaseHelper;
import com.example.projektsklep.MainActivity;
import com.example.projektsklep.ProductsController.ProductAdapter;
import com.example.projektsklep.R;

import java.util.HashMap;

public class ShopFragment extends Fragment {
    MainActivity mainActivity;
    Spinner pcSpinner;
    Spinner mouseSpinner;
    Spinner keyboardSpinner;
    Spinner monitorSpinner;
    DatabaseHelper dbHelper;
    TextView totalPlaceholder;
    Float pcPrice = 0f;
    Float mousePrice = 0f;
    Float keyboardPrice = 0f;
    Float monitorPrice = 0f;
    int mousePriceFactor = 0;
    int keyboardPriceFactor = 0;
    int monitorPriceFactor = 0;
    CheckBox checkBoxMouse;
    CheckBox checkBoxKeyboard;
    CheckBox checkBoxMonitor;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_view, container, false);
        mainActivity = (MainActivity) getActivity();
        dbHelper = new DatabaseHelper(mainActivity);
        totalPlaceholder = view.findViewById(R.id.total_placeholder);

        pcSpinner = view.findViewById(R.id.spinner_pc);
        mouseSpinner = view.findViewById(R.id.spinner_mouse);
        keyboardSpinner = view.findViewById(R.id.spinner_keyboard);
        monitorSpinner = view.findViewById(R.id.spinner_monitor);

        ProductAdapter pcAdapter = new ProductAdapter(getContext(), dbHelper.getProducts("pc"));
        ProductAdapter mouseAdapter = new ProductAdapter(getContext(), dbHelper.getProducts("mouse"));
        ProductAdapter keyboardAdapter = new ProductAdapter(getContext(), dbHelper.getProducts("keyboard"));
        ProductAdapter monitorAdapter = new ProductAdapter(getContext(), dbHelper.getProducts("monitor"));

        pcSpinner.setAdapter(pcAdapter);
        mouseSpinner.setAdapter(mouseAdapter);
        keyboardSpinner.setAdapter(keyboardAdapter);
        monitorSpinner.setAdapter(monitorAdapter);

        initSpinnerListeners();

        checkBoxMouse = view.findViewById(R.id.checkbox_mouse);
        checkBoxKeyboard = view.findViewById(R.id.checkbox_keyboard);
        checkBoxMonitor = view.findViewById(R.id.checkbox_monitor);

        initCheckBoxListeners();

        return view;
    }

    public void initSpinnerListeners() {
        pcSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> hashMap = (HashMap<String, String>) adapterView.getItemAtPosition(i);

                pcPrice = Float.valueOf(hashMap.get("price")) / 100;
                updateTotal();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        mouseSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> hashMap = (HashMap<String, String>) adapterView.getItemAtPosition(i);
                mousePrice = Float.valueOf(hashMap.get("price")) / 100;
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        keyboardSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> hashMap = (HashMap<String, String>) adapterView.getItemAtPosition(i);
                keyboardPrice = Float.valueOf(hashMap.get("price")) / 100;
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        monitorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> hashMap = (HashMap<String, String>) adapterView.getItemAtPosition(i);
                monitorPrice = Float.valueOf(hashMap.get("price")) / 100;
                updateTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void initCheckBoxListeners() {
        checkBoxMouse.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    mousePriceFactor = 1;
                } else {
                    mousePriceFactor = 0;
                }
                updateTotal();
            }
        });

        checkBoxKeyboard.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    keyboardPriceFactor = 1;
                } else {
                    keyboardPriceFactor = 0;
                }
                updateTotal();
            }
        });

        checkBoxMonitor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    monitorPriceFactor = 1;
                } else {
                    monitorPriceFactor = 0;
                }
                updateTotal();
            }
        });

    }

    public float updateTotal() {
        float total = pcPrice + mousePriceFactor * mousePrice + keyboardPriceFactor * keyboardPrice + monitorPriceFactor * monitorPrice;
        totalPlaceholder.setText(total + " zł");
        return total;
    }
}
