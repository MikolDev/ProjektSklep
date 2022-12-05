package com.example.projektsklep.Orders;

import com.example.projektsklep.ProductsModel.CentralUnit;
import com.example.projektsklep.ProductsModel.Keyboard;
import com.example.projektsklep.ProductsModel.Monitor;
import com.example.projektsklep.ProductsModel.Mouse;

import java.util.Date;

public class Order {
    float totalPrice;
    CentralUnit centralUnit;
    Mouse mouse;
    Keyboard keyboard;
    Monitor monitor;
    String ordered;

    public Order(float totalPrice, CentralUnit centralUnit, Mouse mouse, Keyboard keyboard, Monitor monitor, String ordered) {
        this.totalPrice = totalPrice;
        this.centralUnit = centralUnit;
        this.mouse = mouse;
        this.keyboard = keyboard;
        this.monitor = monitor;
        this.ordered = ordered;
    }

    @Override
    public String toString() {
        return "Order{" +
                "totalPrice=" + totalPrice +
                ", centralUnit=" + centralUnit +
                ", mouse=" + mouse +
                ", keyboard=" + keyboard +
                ", monitor=" + monitor +
                ", ordered=" + ordered +
                '}';
    }
}
