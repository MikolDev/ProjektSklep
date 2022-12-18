package com.example.projektsklep.Orders;

import com.google.gson.Gson;

public class OrderState {
    public int isMouse;
    public int isKeyboard;
    public int isMonitor;
    public int pcId;
    public int mouseId;
    public int keyboardId;
    public int monitorId;

    public OrderState(int isMouse, int isKeyboard, int isMonitor, int pcId, int mouseId, int keyboardId, int monitorId) {
        this.isMouse = isMouse;
        this.isKeyboard = isKeyboard;
        this.isMonitor = isMonitor;
        this.pcId = pcId;
        this.mouseId = mouseId;
        this.keyboardId = keyboardId;
        this.monitorId = monitorId;
    }

    public String parseToString() {
        String json = new Gson().toJson(this);
        return json;
    }

    @Override
    public String toString() {
        return "OrderState{" +
                "isMouse=" + isMouse +
                ", isKeyboard=" + isKeyboard +
                ", isMonitor=" + isMonitor +
                ", pcId=" + pcId +
                ", mouseId=" + mouseId +
                ", keyboardId=" + keyboardId +
                ", monitorId=" + monitorId +
                '}';
    }
}
