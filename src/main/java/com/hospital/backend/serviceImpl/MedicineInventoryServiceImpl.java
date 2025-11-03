package com.hospital.backend.serviceImpl;

import com.hospital.backend.entity.MedicalInventory;
import com.hospital.backend.repository.MedicineInventoryRepository;
import com.hospital.backend.service.MedicineInventoryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MedicineInventoryServiceImpl implements MedicineInventoryService {

    @Autowired
    private MedicineInventoryRepository medicineInventoryRepository;

    @Override
    public List<MedicalInventory> getAllMedicines() {
        return medicineInventoryRepository.findAll();
    }

    @Override
    public Optional<MedicalInventory> getMedicineById(int id) {
        return medicineInventoryRepository.findById(id);
    }

    @Override
    public Optional<MedicalInventory> getMedicineByName(String name) {
        return medicineInventoryRepository.findByName(name);
    }

    @Override
    @Transactional
    public MedicalInventory createMedicine(MedicalInventory medicine) {        
        return medicineInventoryRepository.save(medicine);
    }

    @Override
    @Transactional
    public Optional<MedicalInventory> updateMedicine(MedicalInventory medicine) {
        int id = medicine.getMedId();
        return medicineInventoryRepository.findById(id).map(existing -> {            
            existing.setMedName(medicine.getMedName());
            existing.setMedQuantity(medicine.getMedQuantity());
            existing.setMedPrice(medicine.getMedPrice());            
            return medicineInventoryRepository.update(existing);
        });
    }

    @Override
    @Transactional
    public boolean deleteMedicineById(int id) {
        return medicineInventoryRepository.deleteById(id);
    }

    @Override
    @Transactional
    public boolean decreaseQuantity(int id, int amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("amount must be positive");
        }
        return medicineInventoryRepository.decreaseQuantity(id, amount);
    }

    @Override
    @Transactional
    public boolean increaseQuantity(int id, int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        return medicineInventoryRepository.increaseQuantity(id, amount);
}

}
