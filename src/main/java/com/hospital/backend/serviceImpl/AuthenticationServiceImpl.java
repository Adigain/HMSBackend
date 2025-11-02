package com.hospital.backend.serviceImpl;

import com.hospital.backend.entity.LoginRequest;
import com.hospital.backend.entity.LoginResponse;
import com.hospital.backend.entity.Doctor;
import com.hospital.backend.entity.Patient;
import com.hospital.backend.entity.Labtech;
import com.hospital.backend.entity.Pharmacist;
import com.hospital.backend.service.AuthenticationService;
import com.hospital.backend.service.DoctorService;
import com.hospital.backend.service.PatientService;
import com.hospital.backend.service.LabtechService;
import com.hospital.backend.service.PharmacistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private DoctorService doctorService;
    
    @Autowired
    private LabtechService labtechService;

    @Autowired
    private PharmacistService pharmacistService;

    @Autowired
    private PatientService patientService;

    private final Map<String, String> tokenUserMap = new HashMap<>();
    private final String ADMIN_EMAIL = "admin@hospital.com";
    private final String ADMIN_PASSWORD = "admin123";

    @Override
    public LoginResponse authenticateDoctor(LoginRequest loginRequest) {
        // Check if email is provided
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
            return new LoginResponse(null, "DOCTOR", 0, null, null, "Email is required", false);
        }

        // Check if password is provided
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return new LoginResponse(null, "DOCTOR", 0, null, null, "Password is required", false);
        }

        Optional<Doctor> doctor = doctorService.findByEmail(loginRequest.getEmail());
        
        if (!doctor.isPresent()) {
            return new LoginResponse(null, "DOCTOR", 0, null, null, "Doctor not found with this email", false);
        }
        
        if (!loginRequest.getPassword().equals(doctor.get().getPassword())) {
            return new LoginResponse(null, "DOCTOR", 0, null, null, "Incorrect password", false);
        }

        // If we reach here, credentials are valid
        String token = generateToken();
        tokenUserMap.put(token, "DOCTOR_" + doctor.get().getDrId());
        
        return new LoginResponse(
            token,
            "DOCTOR",
            doctor.get().getDrId(),
            doctor.get().getDrName(),
            doctor.get().getEmailId(),
            "Login successful",
            true
        );
    }

    @Override
    public LoginResponse authenticateLabtech(LoginRequest loginRequest) {
        // Check if email is provided
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
            return new LoginResponse(null, "LABTECH", 0, null, null, "Email is required", false);
        }

        // Check if password is provided
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return new LoginResponse(null, "LABTECH", 0, null, null, "Password is required", false);
        }

        Optional<Labtech> labtech = labtechService.findByEmail(loginRequest.getEmail());
        
        if (!labtech.isPresent()) {
            return new LoginResponse(null, "LABTECH", 0, null, null, "Labtech not found with this email", false);
        }
        
        if (!loginRequest.getPassword().equals(labtech.get().getPassword())) {
            return new LoginResponse(null, "LABTECH", 0, null, null, "Incorrect password", false);
        }

        // If we reach here, credentials are valid
        String token = generateToken();
        tokenUserMap.put(token, "LABTECH_" + labtech.get().getLbId());
        
        return new LoginResponse(
            token,
            "LABTECH",
            labtech.get().getLbId(),
            labtech.get().getLbName(),
            labtech.get().getEmailId(),
            "Login successful",
            true
        );
    }

    @Override
    public LoginResponse authenticatePharmacist(LoginRequest loginRequest) {
        // Check if email is provided
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
            return new LoginResponse(null, "PHARMACIST", 0, null, null, "Email is required", false);
        }

        // Check if password is provided
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return new LoginResponse(null, "PHARMACIST", 0, null, null, "Password is required", false);
        }

        Optional<Pharmacist> pharmacist = pharmacistService.findByEmail(loginRequest.getEmail());
        
        if (!pharmacist.isPresent()) {
            return new LoginResponse(null, "PHARMACIST", 0, null, null, "Labtech not found with this email", false);
        }
        
        if (!loginRequest.getPassword().equals(pharmacist.get().getPassword())) {
            return new LoginResponse(null, "PHARMACIST", 0, null, null, "Incorrect password", false);
        }

        // If we reach here, credentials are valid
        String token = generateToken();
        tokenUserMap.put(token, "PHARMACIST_" + pharmacist.get().getPhId());
        
        return new LoginResponse(
            token,
            "PHARMACIST",
            pharmacist.get().getPhId(),
            pharmacist.get().getPhName(),
            pharmacist.get().getEmailId(),
            "Login successful",
            true
        );
    }

    @Override
    public LoginResponse authenticatePatient(LoginRequest loginRequest) {
        // Check if email is provided
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
            return new LoginResponse(null, "PATIENT", 0, null, null, "Email is required", false);
        }

        // Check if password is provided
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return new LoginResponse(null, "PATIENT", 0, null, null, "Password is required", false);
        }

        Optional<Patient> patient = patientService.findByEmail(loginRequest.getEmail());
        
        if (!patient.isPresent()) {
            return new LoginResponse(null, "PATIENT", 0, null, null, "Patient not found with this email", false);
        }
        
        if (!loginRequest.getPassword().equals(patient.get().getPassword())) {
            return new LoginResponse(null, "PATIENT", 0, null, null, "Incorrect password", false);
        }

        // If we reach here, credentials are valid
        String token = generateToken();
        tokenUserMap.put(token, "PATIENT_" + patient.get().getpId());
        
        return new LoginResponse(
            token,
            "PATIENT",
            patient.get().getpId(),
            patient.get().getName(),
            patient.get().getEmail(),
            "Login successful",
            true
        );
    }

    @Override
    public LoginResponse authenticateAdmin(LoginRequest loginRequest) {
        // Check if email is provided
        if (loginRequest.getEmail() == null || loginRequest.getEmail().isEmpty()) {
            return new LoginResponse(null, "ADMIN", 0, null, null, "Email is required", false);
        }

        // Check if password is provided
        if (loginRequest.getPassword() == null || loginRequest.getPassword().isEmpty()) {
            return new LoginResponse(null, "ADMIN", 0, null, null, "Password is required", false);
        }

        if (!loginRequest.getEmail().equals(ADMIN_EMAIL)) {
            return new LoginResponse(null, "ADMIN", 0, null, null, "Invalid admin email", false);
        }
        
        if (!loginRequest.getPassword().equals(ADMIN_PASSWORD)) {
            return new LoginResponse(null, "ADMIN", 0, null, null, "Invalid admin password", false);
        }

        // If we reach here, credentials are valid
        String token = generateToken();
        tokenUserMap.put(token, "ADMIN");
        
        return new LoginResponse(
            token,
            "ADMIN",
            0,
            "Admin",
            ADMIN_EMAIL,
            "Login successful",
            true
        );
    }

    @Override
    public void logout(String token) {
        tokenUserMap.remove(token);
    }

    @Override
    public boolean validateToken(String token) {
        return tokenUserMap.containsKey(token);
    }

    private String generateToken() {
        return UUID.randomUUID().toString();
    }
}