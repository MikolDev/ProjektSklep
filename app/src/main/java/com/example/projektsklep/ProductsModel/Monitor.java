package com.example.projektsklep.ProductsModel;

public class Monitor implements Product {
    private String description;
    private float price;
    private int img;

    public Monitor(String description, float price, int img) {
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public Monitor() {
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
