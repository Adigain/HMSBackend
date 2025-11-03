package com.hospital.backend.service;

import com.hospital.backend.entity.MedicalInventory;

import java.util.List;
import java.util.Optional;

public interface MedicineInventoryService {

    List<MedicalInventory> getAllMedicines();

    Optional<MedicalInventory> getMedicineById(int id);

    Optional<MedicalInventory> getMedicineByName(String name);

    MedicalInventory createMedicine(MedicalInventory medicine);

    Optional<MedicalInventory> updateMedicine(MedicalInventory medicine);

    boolean deleteMedicineById(int id);

    boolean decreaseQuantity(int id, int amount);

    boolean increaseQuantity(int id, int amount);

}
