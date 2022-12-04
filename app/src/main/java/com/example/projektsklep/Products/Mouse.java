package com.example.projektsklep.Products;

public class Mouse {
    private String description;
    private int price;
    private int img;

    public Mouse() {
    }

    public Mouse(String description, int price, int img) {
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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }
}
