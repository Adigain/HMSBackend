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

import com.hospital.backend.entity.Labtech;
import com.hospital.backend.service.LabtechService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/labtechs")
public class LabtechController {
    @Autowired
    private LabtechService labtechService;

    @PostMapping
    public Labtech createLabtech(@RequestBody Labtech labtech) {
        return labtechService.saveLabtech(labtech);
    }
    @GetMapping
    public List<Labtech> getAllLabtechs() {
        return labtechService.getAllLabtechs();
    }

    @GetMapping("/{id}")
    public Labtech getLabtechById(@PathVariable int id) {
        return labtechService.getLabtechById(id)
                .orElseThrow(() -> new RuntimeException("Labtech not found with id: " + id));
    }

    @PutMapping("/{id}")
    public Labtech updateLabtech(@PathVariable int id, @RequestBody Labtech labtech) {
        if (!labtechService.existsById(id)) {
            throw new RuntimeException("Labtech not found with id: " + id);
        }

        labtech.setLbId(id);
        return labtechService.saveLabtech(labtech);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteLabtech(@PathVariable int id) {
        labtechService.deleteLabtech(id);
    }


    @GetMapping("/search")
    public List<Labtech> searchLabtechsByName(@RequestParam String name) {
        return labtechService.searchLabtechsByName(name);
    }

    @GetMapping("/email/{email}")
    public Optional<Labtech> findByEmail(@PathVariable String email) {
        return labtechService.findByEmail(email);
    }

}
