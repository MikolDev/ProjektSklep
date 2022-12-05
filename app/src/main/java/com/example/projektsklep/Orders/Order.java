package com.example.projektsklep.Orders;

import com.example.projektsklep.ProductsModel.CentralUnit;
import com.example.projektsklep.ProductsModel.Keyboard;
import com.example.projektsklep.ProductsModel.Monitor;
import com.example.projektsklep.ProductsModel.Mouse;

import java.util.Date;

public class Order {
    int orderId = -1;
    int totalPrice;
    int centralUnitId;
    int mouseId;
    int keyboardId;
    int monitorId;
    String ordered;
    int userId;

    public Order(int orderId, int totalPrice, int centralUnitId, int mouseId, int keyboardId, int monitorId, String ordered, int userId) {
        this.orderId = orderId;
        this.totalPrice = totalPrice;
        this.centralUnitId = centralUnitId;
        this.mouseId = mouseId;
        this.keyboardId = keyboardId;
        this.monitorId = monitorId;
        this.ordered = ordered;
        this.userId = userId;
    }

    public Order(int totalPrice, int centralUnitId, int mouseId, int keyboardId, int monitorId, String ordered, int userId) {
        this.totalPrice = totalPrice;
        this.centralUnitId = centralUnitId;
        this.mouseId = mouseId;
        this.keyboardId = keyboardId;
        this.monitorId = monitorId;
        this.ordered = ordered;
        this.userId = userId;
    }

    public Order() {
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getCentralUnitId() {
        return centralUnitId;
    }

    public void setCentralUnitId(int centralUnitId) {
        this.centralUnitId = centralUnitId;
    }

    public int getMouseId() {
        return mouseId;
    }

    public void setMouseId(int mouseId) {
        this.mouseId = mouseId;
    }

    public int getKeyboardId() {
        return keyboardId;
    }

    public void setKeyboardId(int keyboardId) {
        this.keyboardId = keyboardId;
    }

    public int getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(int monitorId) {
        this.monitorId = monitorId;
    }

    public String getOrdered() {
        return ordered;
    }

    public void setOrdered(String ordered) {
        this.ordered = ordered;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalPrice=" + totalPrice +
                ", centralUnitId=" + centralUnitId +
                ", mouseId=" + mouseId +
                ", keyboardId=" + keyboardId +
                ", monitorId=" + monitorId +
                ", ordered='" + ordered + '\'' +
                ", userId=" + userId +
                '}';
    }
}
