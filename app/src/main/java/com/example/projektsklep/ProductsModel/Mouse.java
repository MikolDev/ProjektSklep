package com.example.projektsklep.ProductsModel;

public class Mouse implements Product {
    private String description;
    private float price;
    private int img;

    public Mouse() {
    }

    public Mouse(String description, float price, int img) {
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
