package com.hospital.backend.repository;

import com.hospital.backend.entity.MedicalInventory;

import java.util.List;
import java.util.Optional;

public interface MedicineInventoryRepository {

    List<MedicalInventory> findAll();

    Optional<MedicalInventory> findById(int id);

    Optional<MedicalInventory> findByName(String name);

    MedicalInventory save(MedicalInventory medicine); 

    MedicalInventory update(MedicalInventory medicine); 

    boolean deleteById(int id);

    boolean decreaseQuantity(int id, int amount);

    boolean increaseQuantity(int id, int amount);

}
