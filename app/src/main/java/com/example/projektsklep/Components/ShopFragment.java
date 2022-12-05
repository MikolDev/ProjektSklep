package com.example.projektsklep.Components;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
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
import com.example.projektsklep.Orders.Order;
import com.example.projektsklep.ProductsController.ProductAdapter;
import com.example.projektsklep.ProductsModel.CentralUnit;
import com.example.projektsklep.ProductsModel.Keyboard;
import com.example.projektsklep.ProductsModel.Monitor;
import com.example.projektsklep.ProductsModel.Mouse;
import com.example.projektsklep.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

public class ShopFragment extends Fragment {
    MainActivity mainActivity;
    int currentUserId = -1;
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
    float total = 0;
    CheckBox checkBoxMouse;
    CheckBox checkBoxKeyboard;
    CheckBox checkBoxMonitor;
    int curCentralUnitId;
    int curMouseId;
    int curKeyboardId;
    int curMonitorId;
    Button submitButton;
    public static final String TAG = "ORDER";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_view, container, false);
        mainActivity = (MainActivity) getActivity();
        if (mainActivity.getCurrentUser() != null) currentUserId = mainActivity.getCurrentUser().getId();
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

        submitButton = view.findViewById(R.id.order_submit);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currentUserId != -1) {
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    Date currentDate = Calendar.getInstance().getTime();
                    String today = simpleDateFormat.format(currentDate);

                    int orderMouseId = curMouseId;
                    int orderKeyboardId = curKeyboardId;
                    int orderMonitorId = curMonitorId;

                    if (mousePriceFactor == 0) orderMouseId = -1;
                    if (keyboardPriceFactor == 0) orderKeyboardId = -1;
                    if (monitorPriceFactor == 0) orderMonitorId = -1;

                    float orderPrice = total * 100;

                    Order order = new Order((int) orderPrice, curCentralUnitId, orderMouseId, orderKeyboardId, orderMonitorId, today, currentUserId);
                    long success = dbHelper.addOrder(order);
                    if (success > 0) {
                        Toast.makeText(getContext(), getString(R.string.order_taken), Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), getString(R.string.order_error), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), getString(R.string.log_in_to_order), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    public void initSpinnerListeners() {
        pcSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                HashMap<String, String> hashMap = (HashMap<String, String>) adapterView.getItemAtPosition(i);

                pcPrice = Float.valueOf(hashMap.get("price")) / 100;
                int pcId = Integer.parseInt(hashMap.get("id"));
                curCentralUnitId = pcId;

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
                int mouseId = Integer.parseInt(hashMap.get("id"));
                curMouseId = mouseId;

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
                int keyboardId = Integer.parseInt(hashMap.get("id"));
                curKeyboardId = keyboardId;

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
                int monitorId = Integer.parseInt(hashMap.get("id"));
                curMonitorId = monitorId;

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
        total = pcPrice + mousePriceFactor * mousePrice + keyboardPriceFactor * keyboardPrice + monitorPriceFactor * monitorPrice;
        totalPlaceholder.setText(total + " zł");
        return total;
    }
}
