package com.example.projektsklep.Orders;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.projektsklep.DatabaseHelper;
import com.example.projektsklep.ProductsModel.CentralUnit;
import com.example.projektsklep.ProductsModel.Keyboard;
import com.example.projektsklep.ProductsModel.Monitor;
import com.example.projektsklep.ProductsModel.Mouse;
import com.example.projektsklep.R;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    Context context;
    ArrayList<Order> orders;
    DatabaseHelper dbHelper;
    LayoutInflater inflater;

    public OrderAdapter(Context context, ArrayList<Order> orders, DatabaseHelper dbHelper) {
        this.context = context;
        this.orders = orders;
        this.dbHelper = dbHelper;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return orders.size();
    }

    @Override
    public Object getItem(int i) {
        return orders.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Order order = orders.get(i);

        view = inflater.inflate(R.layout.order_item, null);
        TextView dateView = view.findViewById(R.id.order_item_date);
        TextView productsView = view.findViewById(R.id.order_item_products);
        TextView totalView = view.findViewById(R.id.order_item_total);

        ArrayList<String> productInfo = new ArrayList<>();

        CentralUnit centralUnit = new CentralUnit(dbHelper.getProductInfo(order.getCentralUnitId(), "pc"));
        productInfo.add(centralUnit.getDescription() + " " + centralUnit.getPrice() / 100 + " zł");

        if (order.getMouseId() != -1) {
            Mouse mouse = new Mouse(dbHelper.getProductInfo(order.getMouseId(), "mouse"));
            productInfo.add(mouse.getDescription() + " " + mouse.getPrice() / 100 + " zł");
        }

        if (order.getKeyboardId() != -1) {
            Keyboard keyboard = new Keyboard(dbHelper.getProductInfo(order.getKeyboardId(), "keyboard"));
            productInfo.add(keyboard.getDescription() + " " + keyboard.getPrice() / 100 + " zł");
        }

        if (order.getMonitorId() != -1) {
            Monitor monitor = new Monitor(dbHelper.getProductInfo(order.getMonitorId(), "monitor"));
            productInfo.add(monitor.getDescription() + " " + monitor.getPrice() / 100 + " zł");
        }

        dateView.setText(order.getOrdered());
        productInfo.forEach((product) -> {
            productsView.setText(productsView.getText() + product + "\n");
        });
        totalView.setText((order.getTotalPrice() / 100) + " zł");

        Button cancel = view.findViewById(R.id.order_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbHelper.deleteOrder(order.getOrderId());
                OrderAdapter.this.notifyDataSetChanged();
            }
        });

        return view;
    }
}
