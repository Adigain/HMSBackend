package com.hospital.backend.entity;

import java.sql.Timestamp;

public class BillOrder {

    private int billId;
    private int patientId;
    private String type; // "doc", "lab", or "med"
    private int itemId;  // appointment_id, lab_appointment_id, or medicine_order_id
    private double price;
    private String paymentStatus; // "pending" or "paid"
    private Timestamp billingDate;
    
    public BillOrder() {
        this.paymentStatus = "pending";
    }

    public BillOrder(int patientId, String type, int itemId, double price) {
        this.patientId = patientId;
        this.type = type;
        this.itemId = itemId;
        this.price = price;
        this.paymentStatus = "pending";
        this.billingDate = new Timestamp(System.currentTimeMillis());
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Timestamp getBillingDate() {
        return billingDate;
    }

    public void setBillingDate(Timestamp billingDate) {
        this.billingDate = billingDate;
    }
}
