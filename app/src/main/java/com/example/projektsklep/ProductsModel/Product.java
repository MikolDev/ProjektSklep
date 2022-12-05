package com.example.projektsklep.ProductsModel;

public interface Product {
    String description = null;
    int img = 0;
    float price = 0;

    String getDescription();
    float getPrice();
    int getImg();
}
