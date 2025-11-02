package com.hospital.backend.service;

import com.hospital.backend.entity.Pharmacist;


import java.util.List;
import java.util.Optional;

public interface PharmacistService {
    Pharmacist savePharmacist(Pharmacist Pharmacist);
    List<Pharmacist> getAllPharmacists();
    Optional<Pharmacist> getPharmacistById(int id);
    Pharmacist updatePharmacist(int id, Pharmacist Pharmacist);
    void deletePharmacist(int id);
    List<Pharmacist> searchPharmacistsByName(String name);
    Optional<Pharmacist> findByEmail(String email);
	boolean existsById(int id); 
}   