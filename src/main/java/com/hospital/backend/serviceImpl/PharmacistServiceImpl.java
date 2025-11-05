package com.hospital.backend.serviceImpl;

import com.hospital.backend.entity.Pharmacist;
import com.hospital.backend.entity.Patient;
import com.hospital.backend.repository.PharmacistRepository;
import com.hospital.backend.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PharmacistServiceImpl implements PharmacistService {

    @Autowired
    private PharmacistRepository pharmacistRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Pharmacist savePharmacist(Pharmacist pharmacist) {
                
        if (pharmacist.getPassword() != null && !pharmacist.getPassword().isEmpty()) {            
            pharmacist.setPassword(passwordEncoder.encode(pharmacist.getPassword()));
        } else {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        
        return pharmacistRepository.savePharmacist(pharmacist);
    }

    @Override
    public List<Pharmacist> getAllPharmacists() {
        return pharmacistRepository.getAllPharmacists();
    }

    @Override
    public Optional<Pharmacist> getPharmacistById(int id) {
        return pharmacistRepository.getPharmacistById(id);
    }

    @Override
    public Pharmacist updatePharmacist(int id, Pharmacist pharmacist) {
        if (pharmacistRepository.existsById(id)) {
            pharmacist.setPhId(id);                        
            return pharmacistRepository.updatePharmacist(pharmacist);
        }
        throw new RuntimeException("Pharmacist not found with id: " + id);
    }

    @Override
    public void deletePharmacist(int id) {
        pharmacistRepository.deletePharmacist(id);
    }

    @Override
    public Optional<Pharmacist> findByEmail(String email) {
        return pharmacistRepository.findByEmail(email);
    }


	@Override
	public List<Pharmacist> searchPharmacistsByName(String name) {
		return pharmacistRepository.searchPharmacistsByName(name);
	}

	@Override
	public boolean existsById(int id) {
		return pharmacistRepository.existsById(id);
	}
	
}