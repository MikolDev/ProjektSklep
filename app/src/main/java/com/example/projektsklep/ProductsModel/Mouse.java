package com.example.projektsklep.ProductsModel;

public class Mouse implements Product {
    private int productId = -1;
    private String description;
    private float price;
    private int img;

    public Mouse() {
    }

    public Mouse(int productId, String description, float price, int img) {
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public Mouse(String description, float price, int img) {
        this.description = description;
        this.price = price;
        this.img = img;
    }

    public Mouse(String[] productInfo) {
        this.description = productInfo[0];
        this.price = Float.parseFloat(productInfo[1]);
        this.img = Integer.parseInt(productInfo[2]);
    }

    public void setProductId(int productId) {
        this.productId = productId;
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
