package com.example.projektsklep.ProductsModel;

public class Keyboard implements Product {
    private int productId;
    private String description;
    private float price;
    private int img;

    public Keyboard(int productId, String description, float price, int img) {
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public Keyboard(String description, float price, int img) {
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public Keyboard() {
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

    @Override
    public int getProductId() {
        return productId;
    }
}
