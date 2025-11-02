package com.hospital.backend.repository;

import com.hospital.backend.entity.Pharmacist;


import java.util.List;
import java.util.Optional;

public interface PharmacistRepository {
	Pharmacist savePharmacist(Pharmacist pharmacist);
	List<Pharmacist> getAllPharmacists();
    Optional<Pharmacist> getPharmacistById(int id);
    Pharmacist updatePharmacist(Pharmacist pharmacist);
    void deletePharmacist(int id);    
    List<Pharmacist> searchPharmacistsByName(String name);
    Optional<Pharmacist> findByEmail(String email);
    boolean existsById(int id);
}