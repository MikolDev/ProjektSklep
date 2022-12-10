package com.example.projektsklep.Components;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projektsklep.DatabaseHelper;
import com.example.projektsklep.MainActivity;
import com.example.projektsklep.Orders.Order;
import com.example.projektsklep.Orders.OrderAdapter;
import com.example.projektsklep.ProductsController.ProductAdapter;
import com.example.projektsklep.R;

import java.util.ArrayList;

public class OrdersFragment extends Fragment {
    MainActivity mainActivity;
    DatabaseHelper dbHelper;
    int currentUserId;
    View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.orders_view, container, false);
        mainActivity = (MainActivity) getActivity();
        dbHelper = new DatabaseHelper(mainActivity);

        if (mainActivity.getCurrentUser() == null) {
            Toast.makeText(mainActivity.getApplicationContext(), getString(R.string.log_in_orders), Toast.LENGTH_SHORT).show();
        } else {
            currentUserId = mainActivity.getCurrentUser().getId();
            showOrders();
        }

        return view;
    }

    public void showOrders() {
        ListView listView = view.findViewById(R.id.orders_list_view);
        ArrayList<Order> orders = dbHelper.getOrders(currentUserId);
        OrderAdapter orderAdapter = new OrderAdapter(getContext(), orders, dbHelper);
        listView.setAdapter(orderAdapter);

    }
}
