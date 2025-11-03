package com.hospital.backend.entity;

public class MedicineOrderItem {

    private int itemId;
    private int orId;
    private int medId;
    private int quantity;
    private double pricePerItem;

    public MedicineOrderItem() {}

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getOrId() {
        return orId;
    }

    public void setOrId(int orId) {
        this.orId = orId;
    }

    public int getMedId() {
        return medId;
    }

    public void setMedId(int medId) {
        this.medId = medId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public void setPricePerItem(double pricePerItem) {
        this.pricePerItem = pricePerItem;
    }
}
