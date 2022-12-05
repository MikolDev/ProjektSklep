package com.example.projektsklep.ProductsController;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.projektsklep.R;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductAdapter extends BaseAdapter {
    Context context;
    ArrayList<HashMap> repo;
    LayoutInflater layoutInflater;
    ImageView imageView;
    TextView priceView;
    TextView descView;
    TextView productIdView;

    public ProductAdapter(Context context, ArrayList<HashMap> repo) {
        this.context = context;
        this.repo = repo;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return repo.size();
    }

    @Override
    public Object getItem(int i) {
        return repo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        HashMap<String, String> hashMap = repo.get(i);

        int img = Integer.parseInt(hashMap.get("img"));
        float price = Integer.parseInt(hashMap.get("price")) ;
        String productId = hashMap.get("id");

        String desc = hashMap.get("description");

        view = layoutInflater.inflate(R.layout.spinner_product_item, null);
        imageView = view.findViewById(R.id.spinner_image);
        priceView = view.findViewById(R.id.spinner_price);
        productIdView = view.findViewById(R.id.spinner_product_id);
        descView = view.findViewById(R.id.spinner_description);

        imageView.setImageResource(img);
        descView.setText(desc);
        priceView.setText((price / 100) + " zł");
        productIdView.setText(productId);


        return view;
    }
}
