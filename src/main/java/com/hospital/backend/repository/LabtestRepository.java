package com.hospital.backend.repository;

import java.util.List;
import java.util.Optional;
import com.hospital.backend.entity.Labtest;

public interface LabtestRepository {

    Labtest saveLabtest(Labtest labtest);
    Labtest updateLabtest(Labtest labtest);
    void deleteLabtest(int id);
    Optional<Labtest> getLabtestById(int id);
    List<Labtest> getAllLabtests();
    boolean existsById(int id);
    List<Labtest> searchLabtestsByName(String name);
}
