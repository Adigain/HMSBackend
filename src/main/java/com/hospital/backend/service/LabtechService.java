package com.hospital.backend.service;

import com.hospital.backend.entity.Labtech;


import java.util.List;
import java.util.Optional;

public interface LabtechService {
    Labtech saveLabtech(Labtech Labtech);
    List<Labtech> getAllLabtechs();
    Optional<Labtech> getLabtechById(int id);
    Labtech updateLabtech(int id, Labtech Labtech);
    void deleteLabtech(int id);
    List<Labtech> searchLabtechsByName(String name);
    Optional<Labtech> findByEmail(String email);
	boolean existsById(int id); 
}   