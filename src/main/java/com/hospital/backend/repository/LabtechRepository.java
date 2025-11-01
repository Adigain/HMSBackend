package com.hospital.backend.repository;

import com.hospital.backend.entity.Labtech;


import java.util.List;
import java.util.Optional;

public interface LabtechRepository {
	Labtech saveLabtech(Labtech labtech);
	List<Labtech> getAllLabtechs();
    Optional<Labtech> getLabtechById(int id);
    Labtech updateLabtech(Labtech labtech);
    void deleteLabtech(int id);    
    List<Labtech> searchLabtechsByName(String name);
    Optional<Labtech> findByEmail(String email);
    boolean existsById(int id);
}