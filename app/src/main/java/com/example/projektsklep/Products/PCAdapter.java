package com.example.projektsklep.Products;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projektsklep.R;

import java.util.ArrayList;

public class PCAdapter extends BaseAdapter {
    Context context;
    ArrayList<CentralUnit> centralUnits;
    LayoutInflater layoutInflater;
    ImageView imageView;
    TextView priceView;
    TextView descView;

    public PCAdapter(Context context, ArrayList<CentralUnit> centralUnits) {
        this.context = context;
        this.centralUnits = centralUnits;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return centralUnits.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CentralUnit currentPC = centralUnits.get(i);
        int img = currentPC.getImg();
        int price = currentPC.getPrice() / 100;
        String desc = currentPC.getDescription();

        view = layoutInflater.inflate(R.layout.spinner_pc_item, null);
        imageView = view.findViewById(R.id.pc_image);
        priceView = view.findViewById(R.id.pc_price);
        descView = view.findViewById(R.id.pc_desc);

        imageView.setImageResource(img);
        priceView.setText(price);
        descView.setText(desc);

        return view;
    }
}
