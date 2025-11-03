package com.hospital.backend.entity;

import java.sql.Timestamp;
import java.util.List;

public class MedicineOrder {

    private int orId;
    private int pId;
    private int drId;
    private double totalPrice;
    private String status; 
    private Timestamp orderDate;
    private List<MedicineOrderItem> items;

    public MedicineOrder() {}

    public int getOrId() {
        return orId;
    }

    public void setOrId(int orId) {
        this.orId = orId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public int getDrId() {
        return drId;
    }

    public void setDrId(int drId) {
        this.drId = drId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public List<MedicineOrderItem> getItems() {
        return items;
    }

    public void setItems(List<MedicineOrderItem> items) {
        this.items = items;
    }
}
