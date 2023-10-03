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

/**
 * Adapter do spinnerów z widoku sklepu. Dane o produktach pobierane są ze źródła i są wyświetlane użytkownikowi do wyboru jako pozycje spinnera.
 */
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

    /**
     * Zwraca długość listy produktów.
     * @return długość listy
     */
    @Override
    public int getCount() {
        return repo.size();
    }

    /**
     * Zwraca obiekt z listy o danym id.
     * @param i index obiektu do znalezienia
     * @return szukany obiekt
     */
    @Override
    public Object getItem(int i) {
        return repo.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    /**
     * Metoda obsługuje widok pojedynczej pozycji spinnera. Ustawia dane produktu w widoku.
     *
     * @param i index z listy produktów
     * @param view widok
     * @param viewGroup
     * @return widok z wypełnionymi danymi
     */
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
