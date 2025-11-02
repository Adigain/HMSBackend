package com.hospital.backend.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.hospital.backend.entity.Pharmacist;
import com.hospital.backend.service.PharmacistService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/pharmacists")
public class PharmacistController {
    @Autowired
    private PharmacistService pharmacistService;

    @PostMapping
    public Pharmacist createPharmacist(@RequestBody Pharmacist pharmacist) {
        return pharmacistService.savePharmacist(pharmacist);
    }
    @GetMapping
    public List<Pharmacist> getAllPharmacists() {
        return pharmacistService.getAllPharmacists();
    }

    @GetMapping("/{id}")
    public Pharmacist getPharmacistById(@PathVariable int id) {
        return pharmacistService.getPharmacistById(id)
                .orElseThrow(() -> new RuntimeException("Pharmacist not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Pharmacist updatePharmacist(@PathVariable int id, @RequestBody Pharmacist pharmacist) {
        if (!pharmacistService.existsById(id)) {
            throw new RuntimeException("Pharmacist not found with id: " + id);
        }

        pharmacist.setPhId(id);
        return pharmacistService.savePharmacist(pharmacist);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePharmacist(@PathVariable int id) {
        pharmacistService.deletePharmacist(id);
    }


    @GetMapping("/search")
    public List<Pharmacist> searchPharmacistsByName(@RequestParam String name) {
        return pharmacistService.searchPharmacistsByName(name);
    }

    @GetMapping("/email/{email}")
    public Optional<Pharmacist> findByEmail(@PathVariable String email) {
        return pharmacistService.findByEmail(email);
    }

}
