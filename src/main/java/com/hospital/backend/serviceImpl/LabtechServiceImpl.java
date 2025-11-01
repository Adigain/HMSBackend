package com.hospital.backend.serviceImpl;

import com.hospital.backend.entity.Labtech;
import com.hospital.backend.entity.Patient;
import com.hospital.backend.repository.LabtechRepository;
import com.hospital.backend.service.LabtechService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LabtechServiceImpl implements LabtechService {

    @Autowired
    private LabtechRepository labtechRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Labtech saveLabtech(Labtech labtech) {
        // Add null check for password
        if (labtech.getPassword() != null && !labtech.getPassword().isEmpty()) {
            // Skip encoding - use password as-is
            System.out.println("Password received: " + labtech.getPassword()); // Debug log
            // Note: password will be stored as plain text
        } else {
            throw new IllegalArgumentException("Password cannot be empty");
        }
        
        return labtechRepository.saveLabtech(labtech);
    }

    @Override
    public List<Labtech> getAllLabtechs() {
        return labtechRepository.getAllLabtechs();
    }

    @Override
    public Optional<Labtech> getLabtechById(int id) {
        return labtechRepository.getLabtechById(id);
    }

    @Override
    public Labtech updateLabtech(int id, Labtech labtech) {
        if (labtechRepository.existsById(id)) {
            labtech.setLbId(id);
            return labtechRepository.updateLabtech(labtech);
        }
        throw new RuntimeException("Labtech not found with id: " + id);
    }

    @Override
    public void deleteLabtech(int id) {
        labtechRepository.deleteLabtech(id);
    }

    @Override
    public Optional<Labtech> findByEmail(String email) {
        return labtechRepository.findByEmail(email);
    }


	@Override
	public List<Labtech> searchLabtechsByName(String name) {
		return labtechRepository.searchLabtechsByName(name);
	}

	@Override
	public boolean existsById(int id) {
		return labtechRepository.existsById(id);
	}
	
}