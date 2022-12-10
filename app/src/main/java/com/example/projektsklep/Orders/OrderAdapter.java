package com.example.projektsklep.Orders;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    public View getView(int position, View view, ViewGroup viewGroup) {
        Order order = orders.get(position);

        view = inflater.inflate(R.layout.order_item, null);
        TextView dateView = view.findViewById(R.id.order_item_date);
        TextView productsView = view.findViewById(R.id.order_item_products);
        TextView totalView = view.findViewById(R.id.order_item_total);

        ArrayList<String> productInfo = new ArrayList<>();

        CentralUnit centralUnit = new CentralUnit(dbHelper.getProductInfo(order.getCentralUnitId(), "pc"));

        productInfo.add(centralUnit.getDescription() + " - " + centralUnit.getPrice() / 100 + " zł");

        if (order.getMouseId() != -1) {
            Mouse mouse = new Mouse(dbHelper.getProductInfo(order.getMouseId(), "mouse"));
            productInfo.add(mouse.getDescription() + " - " + mouse.getPrice() / 100 + " zł");
        }

        if (order.getKeyboardId() != -1) {
            Keyboard keyboard = new Keyboard(dbHelper.getProductInfo(order.getKeyboardId(), "keyboard"));
            productInfo.add(keyboard.getDescription() + " - " + keyboard.getPrice() / 100 + " zł");
        }

        if (order.getMonitorId() != -1) {
            Monitor monitor = new Monitor(dbHelper.getProductInfo(order.getMonitorId(), "monitor"));
            productInfo.add(monitor.getDescription() + " - " + monitor.getPrice() / 100 + " zł");
        }

        dateView.setText(order.getOrdered());
        productInfo.forEach((product) -> {
            productsView.setText(productsView.getText() + product);
            if (!product.equals(productInfo.get(productInfo.size() - 1))) {
                productsView.setText(productsView.getText() + "\n\n");
            }
        });
        totalView.setText((order.getTotalPrice() / 100) + " zł");

        Button cancel = view.findViewById(R.id.order_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch(i) {
                            case DialogInterface.BUTTON_POSITIVE:
                                deleteItem(order, position);
                                dialogInterface.dismiss();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                dialogInterface.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(context.getString(R.string.order_sure))
                        .setPositiveButton(context.getString(R.string.yes), dialogClickListener)
                        .setNegativeButton(context.getString(R.string.no), dialogClickListener).show();
            }
        });

        notifyDataSetChanged();
        return view;
    }

    private void deleteItem(Order order, int i) {
        long success = dbHelper.deleteOrder(order.getOrderId());
        OrderAdapter.this.notifyDataSetChanged();
        if (success == 1) {
            Toast.makeText(context, context.getString(R.string.order_cancelled), Toast.LENGTH_SHORT).show();
            orders.remove(i);
            notifyDataSetChanged();
        }
    }
}
