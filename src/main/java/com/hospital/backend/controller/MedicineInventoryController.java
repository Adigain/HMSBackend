package com.hospital.backend.controller;

import com.hospital.backend.entity.MedicalInventory;
import com.hospital.backend.service.MedicineInventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicine")
@CrossOrigin(origins = "*")
public class MedicineInventoryController {

    @Autowired
    private MedicineInventoryService medicineInventoryService;

    @GetMapping
    public List<MedicalInventory> getAllMedicines() {
        return medicineInventoryService.getAllMedicines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicalInventory> getMedicineById(@PathVariable int id) {
        return medicineInventoryService.getMedicineById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<MedicalInventory> getMedicineByName(@PathVariable String name) {
        return medicineInventoryService.getMedicineByName(name)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<MedicalInventory> createMedicine(@RequestBody MedicalInventory medicalInventory) {
        return new ResponseEntity<>(medicineInventoryService.createMedicine(medicalInventory), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalInventory> updateMedicine(@PathVariable int id, @RequestBody MedicalInventory medicalInventory) {
        medicalInventory.setMedId(id);
        return medicineInventoryService.updateMedicine(medicalInventory)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedicine(@PathVariable int id) {
        return medicineInventoryService.deleteMedicineById(id)
                ? ResponseEntity.ok().build()
                : ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}/decrease")
    public ResponseEntity<String> decreaseQuantity(@PathVariable int id, @RequestParam int amount) {
        boolean success = medicineInventoryService.decreaseQuantity(id, amount);
        return success
                ? ResponseEntity.ok("Quantity decreased successfully.")
                : ResponseEntity.badRequest().body("Not enough quantity or medicine not found.");
    }

    @PatchMapping("/{id}/increase")
    public ResponseEntity<String> increaseQuantity(@PathVariable int id, @RequestParam int amount) {
        boolean success = medicineInventoryService.increaseQuantity(id, amount);
        return success
                ? ResponseEntity.ok("Quantity increased successfully.")
                : ResponseEntity.badRequest().body("Medicine not found.");
}
}
