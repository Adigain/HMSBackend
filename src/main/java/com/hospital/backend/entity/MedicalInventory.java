package com.hospital.backend.entity;

public class MedicalInventory {

    private int medId;
    private String medName;
    private int medQuantity;
    private double medPrice;

    public MedicalInventory() {}

    public MedicalInventory(int medId, String medName, int medQuantity, double medPrice) {
        this.medId = medId;
        this.medName = medName;
        this.medQuantity = medQuantity;
        this.medPrice = medPrice;
    }
    public int getMedId() { return medId; }
    public void setMedId(int medId) { this.medId = medId; }
    public String getMedName() { return medName; }
    public void setMedName(String medName) { this.medName = medName; }
    public int getMedQuantity() { return medQuantity; }
    public void setMedQuantity(int medQuantity) { this.medQuantity = medQuantity; }
    public double getMedPrice() { return medPrice; }
    public void setMedPrice(double medPrice) { this.medPrice = medPrice; }
}
