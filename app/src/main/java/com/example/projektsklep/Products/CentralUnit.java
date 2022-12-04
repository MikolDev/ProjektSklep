package com.example.projektsklep.Products;

public class CentralUnit implements Product {
    private String description;
    private float price;
    private int img;

    public CentralUnit(String description, float price, int img) {
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public CentralUnit() {
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
